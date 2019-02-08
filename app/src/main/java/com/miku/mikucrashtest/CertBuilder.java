package com.miku.mikucrashtest;

import android.content.Context;
import android.content.SharedPreferences;

import org.spongycastle.asn1.x500.X500Name;
import org.spongycastle.asn1.x500.X500NameBuilder;
import org.spongycastle.asn1.x500.style.BCStyle;
import org.spongycastle.asn1.x509.SubjectPublicKeyInfo;
import org.spongycastle.cert.X509CertificateHolder;
import org.spongycastle.cert.X509v1CertificateBuilder;
import org.spongycastle.jce.provider.BouncyCastleProvider;
import org.spongycastle.operator.ContentSigner;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.operator.jcajce.JcaContentSignerBuilder;
import org.spongycastle.util.io.pem.PemObject;
import org.spongycastle.util.io.pem.PemWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Date;
import java.util.Random;

public class CertBuilder {

    static {
        Security.insertProviderAt(new org.spongycastle.jce.provider.BouncyCastleProvider(), 1);
    }

    private static final long VALIDITY_IN_DAYS = 3650;

    public static String getCertificate(Context context, String userId) {
        final SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preferences), Context.MODE_PRIVATE);
        final String certificateKey = context.getString(R.string.preferences_certificate) + "." + userId;
        return sharedPref.getString(certificateKey, null);
    }

    public static String getFingerprint(Context context, String userId) {
        final SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preferences), Context.MODE_PRIVATE);
        final String fingerprintKey = context.getString(R.string.preferences_fingerprint) + "." + userId;
        return sharedPref.getString(fingerprintKey, null);
    }

    public static void generateCertificate(Context context, String userId) {
        KeyPair keyPair = generateKeyPair();
        if (keyPair == null) return;
        X509CertificateHolder certificateHolder = generateCertificate(keyPair);
        if (certificateHolder == null) return;
        String pem = generatePem(keyPair, certificateHolder);
        String fingerprint = generateFingerprint(certificateHolder);

        final SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preferences), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPref.edit();
        final String certificateKey = context.getString(R.string.preferences_certificate) + "." + userId;
        final String fingerprintKey = context.getString(R.string.preferences_fingerprint) + "." + userId;
        editor.putString(certificateKey, pem);
        editor.putString(fingerprintKey, fingerprint);
        editor.apply();
    }

    private static KeyPair generateKeyPair() {
        KeyPairGenerator kpg = null;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (kpg == null) return null;
        kpg.initialize(2048);
        return kpg.genKeyPair();
    }

    private static X509CertificateHolder generateCertificate(KeyPair keyPair) {
        Date startDate = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        Date endDate = new Date(System.currentTimeMillis() + VALIDITY_IN_DAYS * 24 * 60 * 60 * 1000);

        X500NameBuilder nameBuilder = new X500NameBuilder(BCStyle.INSTANCE);
        nameBuilder.addRDN(BCStyle.CN, "WebRTC");

        X500Name x500Name = nameBuilder.build();
        Random random = new Random();

        SubjectPublicKeyInfo subjectPublicKeyInfo = SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded());
        X509v1CertificateBuilder v1CertGen = new X509v1CertificateBuilder(x500Name
                , BigInteger.valueOf(random.nextLong())
                ,startDate
                ,endDate
                ,x500Name
                ,subjectPublicKeyInfo);

        // Prepare Signature:
        ContentSigner sigGen = null;
        try {
            Security.addProvider(new BouncyCastleProvider());
            sigGen = new JcaContentSignerBuilder("SHA256WithRSAEncryption").setProvider("SC").build(keyPair.getPrivate());
        } catch (OperatorCreationException e) {
            e.printStackTrace();
        }
        // Self sign :
        if (sigGen == null) return null;
        return v1CertGen.build(sigGen);
    }

    private static String generatePem(KeyPair keyPair, X509CertificateHolder certificateHolder) {
        try {
            final StringWriter privateStringWriter = new StringWriter();
            final PemWriter privatePemWriter = new PemWriter(privateStringWriter);
            final PemObject privatePemObject = new PemObject("PRIVATE KEY", keyPair.getPrivate().getEncoded());
            privatePemWriter.writeObject(privatePemObject);
            privatePemWriter.flush();
            String privateKey = privateStringWriter.toString();

            final StringWriter stringWriter = new StringWriter();
            final PemWriter pemWriter = new PemWriter(stringWriter);
            final PemObject pemObject = new PemObject("CERTIFICATE", certificateHolder.getEncoded());
            pemWriter.writeObject(pemObject);
            pemWriter.flush();
            String certString = stringWriter.toString();
            return privateKey + "|" + certString;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generateFingerprint(X509CertificateHolder certificateHolder) {
        try {
            return hash256(certificateHolder.getEncoded()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    private static String hash256(byte[] bytes) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(bytes);
        return bytesToHex(md.digest());
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte byt : bytes) result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }

}