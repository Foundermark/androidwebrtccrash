# Android Miku WebRTC Crash Test
This app will attempt to connect to a publicly available Miku baby monitor with a hard-coded certificate. The app will crash the following devices after 45 minutes to an hour:
- Samsung Galaxy S6
- Samsung Galaxy S9

## Crash
```
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128] JNI ERROR (app bug): weak global reference table overflow (max=51200)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128] weak global reference table dump:
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]   Last 10 entries (of 51200):
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]     51199: 0x12f53f70 java.lang.Thread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]     51198: 0x12f53ee0 java.lang.Thread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]     51197: 0x12f53e50 java.lang.Thread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]     51196: 0x12f53dc0 java.lang.Thread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]     51195: 0x12f53d30 java.lang.Thread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]     51194: 0x12f53ca0 java.lang.Thread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]     51193: 0x12f53c10 java.lang.Thread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]     51192: 0x12f53b80 java.lang.Thread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]     51191: 0x12f53af0 java.lang.Thread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]     51190: 0x12f53a60 java.lang.Thread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]   Summary:
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of java.util.TimerThread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of org.webrtc.audio.WebRtcAudioTrack$AudioTrackThread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of org.webrtc.audio.WebRtcAudioRecord$AudioRecordThread
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of org.webrtc.AndroidVideoDecoder$1
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]       300 of java.lang.Thread (300 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]      5127 of java.lang.Class (5127 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         3 of byte[] (2496 elements) (3 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         3 of byte[] (8976 elements) (3 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of byte[] (12288 elements)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         5 of byte[] (16384 elements) (5 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         4 of byte[] (17920 elements) (4 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of byte[] (26240 elements)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of byte[] (30720 elements)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         9 of byte[] (36864 elements) (9 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]        27 of byte[] (46656 elements) (27 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of byte[] (57344 elements)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of byte[] (57408 elements)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         2 of byte[] (61952 elements) (2 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         2 of byte[] (62500 elements) (2 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]        17 of byte[] (65536 elements) (17 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         3 of byte[] (82944 elements) (3 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         2 of byte[] (84480 elements) (2 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         3 of byte[] (141312 elements) (3 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of byte[] (146432 elements)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         3 of byte[] (147456 elements) (3 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         4 of byte[] (195536 elements) (4 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         5 of byte[] (478864 elements) (5 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of java.lang.ThreadGroup
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]        30 of java.lang.DexCache (30 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         3 of dalvik.system.PathClassLoader (1 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         1 of android.media.MediaCodec
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]         3 of android.os.HandlerThread (3 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128]        19 of android.view.RenderNode (19 unique instances)
2019-02-12 15:03:49.954 3023-3148/com.miku.mikucrashtest A/art: art/runtime/indirect_reference_table.cc:128] 
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] Runtime aborting...
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] Aborting thread:
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "JNISurfaceTextureContext" prio=10 tid=21 Runnable
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=0 dsCount=0 obj=0x12c210d0 self=0x74a7fd5a00
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3148 nice=-16 cgrp=default sched=0/0 handle=0x74a7af8450
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=R schedstat=( 0 0 0 ) utm=6296 stm=5184 core=2 HZ=100
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74a79fe000-0x74a7a00000 stackSize=1005KB
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes= "abort lock" "JNI weak global reference table lock" "ObjectRegistry lock" "mutator lock"(shared held)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 0000000000477c38  /system/lib64/libart.so (_ZN3art15DumpNativeStackERNSt3__113basic_ostreamIcNS0_11char_traitsIcEEEEiP12BacktraceMapPKcPNS_9ArtMethodEPv+220)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 0000000000477c34  /system/lib64/libart.so (_ZN3art15DumpNativeStackERNSt3__113basic_ostreamIcNS0_11char_traitsIcEEEEiP12BacktraceMapPKcPNS_9ArtMethodEPv+216)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 000000000044c1a8  /system/lib64/libart.so (_ZNK3art6Thread9DumpStackERNSt3__113basic_ostreamIcNS1_11char_traitsIcEEEEbP12BacktraceMap+472)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 000000000043a210  /system/lib64/libart.so (_ZNK3art10AbortState10DumpThreadERNSt3__113basic_ostreamIcNS1_11char_traitsIcEEEEPNS_6ThreadE+56)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 000000000043a030  /system/lib64/libart.so (_ZNK3art10AbortState4DumpERNSt3__113basic_ostreamIcNS1_11char_traitsIcEEEE+576)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 000000000042dc78  /system/lib64/libart.so (_ZN3art7Runtime5AbortEv+140)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #06 pc 00000000000e4fac  /system/lib64/libart.so (_ZN3art10LogMessageD2Ev+1204)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #07 pc 000000000024bb6c  /system/lib64/libart.so (_ZN3art22IndirectReferenceTable3AddEjPNS_6mirror6ObjectE+308)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #08 pc 00000000002ee110  /system/lib64/libart.so (_ZN3art9JavaVMExt16AddWeakGlobalRefEPNS_6ThreadEPNS_6mirror6ObjectE+80)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #09 pc 000000000034d910  /system/lib64/libart.so (_ZN3art3JNI16NewWeakGlobalRefEP7_JNIEnvP8_jobject+600)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #10 pc 0000000000102ec0  /system/lib64/libart.so (_ZN3art8CheckJNI6NewRefEPKcP7_JNIEnvP8_jobjectNS_15IndirectRefKindE+708)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #11 pc 00000000003076cc  /system/lib64/libart.so (_ZN3art14ObjectRegistry11InternalAddINS_6mirror6ObjectEEEmNS_6HandleIT_EE+1104)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #12 pc 0000000000307fc0  /system/lib64/libart.so (_ZN3art14ObjectRegistry3AddEPNS_6mirror6ObjectE+152)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #13 pc 0000000000168888  /system/lib64/libart.so (_ZN3art3Dbg11GetThreadIdEPNS_6ThreadE+632)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #14 pc 00000000002f87e4  /system/lib64/libart.so (_ZN3art4JDWP9JdwpState16PostThreadChangeEPNS_6ThreadEb+872)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #15 pc 0000000000177394  /system/lib64/libart.so (_ZN3art3Dbg15PostThreadStartEPNS_6ThreadE+48)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #16 pc 000000000044aaac  /system/lib64/libart.so (_ZN3art6Thread6AttachEPKcbP8_jobjectb+2560)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #17 pc 000000000042d5b0  /system/lib64/libart.so (_ZN3art7Runtime19AttachCurrentThreadEPKcbP8_jobjectb+112)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #18 pc 00000000002f22a8  /system/lib64/libart.so (_ZN3art3JII27AttachCurrentThreadInternalEP7_JavaVMPP7_JNIEnvPvb+296)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #19 pc 000000000010aa08  /system/lib64/libart.so (_ZN3art8CheckJII19AttachCurrentThreadEP7_JavaVMPP7_JNIEnvPv+132)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #20 pc 0000000000120d00  /system/lib64/libandroid_runtime.so (_ZN7android24JNISurfaceTextureContext9getJNIEnvEPb+156)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #21 pc 0000000000120ed8  /system/lib64/libandroid_runtime.so (_ZN7android24JNISurfaceTextureContext16onFrameAvailableERKNS_10BufferItemE+44)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #22 pc 00000000000643e8  /system/lib64/libgui.so (_ZN7android12ConsumerBase16onFrameAvailableERKNS_10BufferItemE+160)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #23 pc 0000000000058e7c  /system/lib64/libgui.so (_ZN7android11BufferQueue21ProxyConsumerListener16onFrameAvailableERKNS_10BufferItemE+104)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #24 pc 0000000000060f14  /system/lib64/libgui.so (_ZN7android19BufferQueueProducer11queueBufferEiRKNS_22IGraphicBufferProducer16QueueBufferInputEPNS1_17QueueBufferOutputE+2016)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #25 pc 000000000007d884  /system/lib64/libgui.so (_ZN7android7Surface11queueBufferEP19ANativeWindowBufferi+960)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #26 pc 00000000000fd250  /system/lib64/libstagefright.so (_ZN7android6ACodec9BaseState21onOutputBufferDrainedERKNS_2spINS_8AMessageEEE+1120)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #27 pc 00000000000fafac  /system/lib64/libstagefright.so (_ZN7android6ACodec9BaseState17onMessageReceivedERKNS_2spINS_8AMessageEEE+1076)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #28 pc 00000000000123ac  /system/lib64/libstagefright_foundation.so (_ZN7android25AHierarchicalStateMachine13handleMessageERKNS_2spINS_8AMessageEEE+136)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #29 pc 0000000000012118  /system/lib64/libstagefright_foundation.so (_ZN7android8AHandler14deliverMessageERKNS_2spINS_8AMessageEEE+52)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #30 pc 0000000000015ebc  /system/lib64/libstagefright_foundation.so (_ZN7android8AMessage7deliverEv+112)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #31 pc 0000000000013248  /system/lib64/libstagefright_foundation.so (_ZN7android7ALooper4loopEv+476)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #32 pc 0000000000012488  /system/lib64/libutils.so (_ZN7android6Thread11_threadLoopEPv+272)
2019-02-12 15:03:50.494 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #33 pc 0000000000068258  /system/lib64/libc.so (_ZL15__pthread_startPv+196)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #34 pc 000000000001dc00  /system/lib64/libc.so (__start_thread+16)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   (no managed stack frames)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] Dumping all threads without appropriate locks held: thread list lock
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] All threads:
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] DALVIK THREADS (31):
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "JNISurfaceTextureContext" prio=10 tid=21 Runnable
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=0 dsCount=0 obj=0x12c210d0 self=0x74a7fd5a00
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3148 nice=-16 cgrp=default sched=0/0 handle=0x74a7af8450
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=R schedstat=( 0 0 0 ) utm=6303 stm=5190 core=6 HZ=100
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74a79fe000-0x74a7a00000 stackSize=1005KB
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes= "abort lock" "JNI weak global reference table lock" "ObjectRegistry lock" "mutator lock"(shared held)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 0000000000477c38  /system/lib64/libart.so (_ZN3art15DumpNativeStackERNSt3__113basic_ostreamIcNS0_11char_traitsIcEEEEiP12BacktraceMapPKcPNS_9ArtMethodEPv+220)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 0000000000477c34  /system/lib64/libart.so (_ZN3art15DumpNativeStackERNSt3__113basic_ostreamIcNS0_11char_traitsIcEEEEiP12BacktraceMapPKcPNS_9ArtMethodEPv+216)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 000000000044c1a8  /system/lib64/libart.so (_ZNK3art6Thread9DumpStackERNSt3__113basic_ostreamIcNS1_11char_traitsIcEEEEbP12BacktraceMap+472)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 0000000000463ba0  /system/lib64/libart.so (_ZN3art14DumpCheckpoint3RunEPNS_6ThreadE+820)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 000000000045bba8  /system/lib64/libart.so (_ZN3art10ThreadList13RunCheckpointEPNS_7ClosureE+456)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 000000000045b7b8  /system/lib64/libart.so (_ZN3art10ThreadList4DumpERNSt3__113basic_ostreamIcNS1_11char_traitsIcEEEEb+288)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #06 pc 000000000043a040  /system/lib64/libart.so (_ZNK3art10AbortState4DumpERNSt3__113basic_ostreamIcNS1_11char_traitsIcEEEE+592)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #07 pc 000000000042dc78  /system/lib64/libart.so (_ZN3art7Runtime5AbortEv+140)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #08 pc 00000000000e4fac  /system/lib64/libart.so (_ZN3art10LogMessageD2Ev+1204)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #09 pc 000000000024bb6c  /system/lib64/libart.so (_ZN3art22IndirectReferenceTable3AddEjPNS_6mirror6ObjectE+308)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #10 pc 00000000002ee110  /system/lib64/libart.so (_ZN3art9JavaVMExt16AddWeakGlobalRefEPNS_6ThreadEPNS_6mirror6ObjectE+80)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #11 pc 000000000034d910  /system/lib64/libart.so (_ZN3art3JNI16NewWeakGlobalRefEP7_JNIEnvP8_jobject+600)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #12 pc 0000000000102ec0  /system/lib64/libart.so (_ZN3art8CheckJNI6NewRefEPKcP7_JNIEnvP8_jobjectNS_15IndirectRefKindE+708)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #13 pc 00000000003076cc  /system/lib64/libart.so (_ZN3art14ObjectRegistry11InternalAddINS_6mirror6ObjectEEEmNS_6HandleIT_EE+1104)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #14 pc 0000000000307fc0  /system/lib64/libart.so (_ZN3art14ObjectRegistry3AddEPNS_6mirror6ObjectE+152)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #15 pc 0000000000168888  /system/lib64/libart.so (_ZN3art3Dbg11GetThreadIdEPNS_6ThreadE+632)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #16 pc 00000000002f87e4  /system/lib64/libart.so (_ZN3art4JDWP9JdwpState16PostThreadChangeEPNS_6ThreadEb+872)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #17 pc 0000000000177394  /system/lib64/libart.so (_ZN3art3Dbg15PostThreadStartEPNS_6ThreadE+48)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #18 pc 000000000044aaac  /system/lib64/libart.so (_ZN3art6Thread6AttachEPKcbP8_jobjectb+2560)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #19 pc 000000000042d5b0  /system/lib64/libart.so (_ZN3art7Runtime19AttachCurrentThreadEPKcbP8_jobjectb+112)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #20 pc 00000000002f22a8  /system/lib64/libart.so (_ZN3art3JII27AttachCurrentThreadInternalEP7_JavaVMPP7_JNIEnvPvb+296)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #21 pc 000000000010aa08  /system/lib64/libart.so (_ZN3art8CheckJII19AttachCurrentThreadEP7_JavaVMPP7_JNIEnvPv+132)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #22 pc 0000000000120d00  /system/lib64/libandroid_runtime.so (_ZN7android24JNISurfaceTextureContext9getJNIEnvEPb+156)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #23 pc 0000000000120ed8  /system/lib64/libandroid_runtime.so (_ZN7android24JNISurfaceTextureContext16onFrameAvailableERKNS_10BufferItemE+44)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #24 pc 00000000000643e8  /system/lib64/libgui.so (_ZN7android12ConsumerBase16onFrameAvailableERKNS_10BufferItemE+160)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #25 pc 0000000000058e7c  /system/lib64/libgui.so (_ZN7android11BufferQueue21ProxyConsumerListener16onFrameAvailableERKNS_10BufferItemE+104)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #26 pc 0000000000060f14  /system/lib64/libgui.so (_ZN7android19BufferQueueProducer11queueBufferEiRKNS_22IGraphicBufferProducer16QueueBufferInputEPNS1_17QueueBufferOutputE+2016)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #27 pc 000000000007d884  /system/lib64/libgui.so (_ZN7android7Surface11queueBufferEP19ANativeWindowBufferi+960)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #28 pc 00000000000fd250  /system/lib64/libstagefright.so (_ZN7android6ACodec9BaseState21onOutputBufferDrainedERKNS_2spINS_8AMessageEEE+1120)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #29 pc 00000000000fafac  /system/lib64/libstagefright.so (_ZN7android6ACodec9BaseState17onMessageReceivedERKNS_2spINS_8AMessageEEE+1076)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #30 pc 00000000000123ac  /system/lib64/libstagefright_foundation.so (_ZN7android25AHierarchicalStateMachine13handleMessageERKNS_2spINS_8AMessageEEE+136)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #31 pc 0000000000012118  /system/lib64/libstagefright_foundation.so (_ZN7android8AHandler14deliverMessageERKNS_2spINS_8AMessageEEE+52)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #32 pc 0000000000015ebc  /system/lib64/libstagefright_foundation.so (_ZN7android8AMessage7deliverEv+112)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #33 pc 0000000000013248  /system/lib64/libstagefright_foundation.so (_ZN7android7ALooper4loopEv+476)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #34 pc 0000000000012488  /system/lib64/libutils.so (_ZN7android6Thread11_threadLoopEPv+272)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #35 pc 0000000000068258  /system/lib64/libc.so (_ZL15__pthread_startPv+196)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #36 pc 000000000001dc00  /system/lib64/libc.so (__start_thread+16)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   (no managed stack frames)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "main" prio=10 tid=1 Native
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x75ddf418 self=0x74d9cc7a00
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3023 nice=0 cgrp=default sched=0/0 handle=0x74de693a98
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=4730 stm=1434 core=2 HZ=100
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x7ff3b12000-0x7ff3b14000 stackSize=8MB
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_epoll_wait+0x2ac/0x36c
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_epoll_pwait+0xa8/0x124
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000006a5f4  /system/lib64/libc.so (__epoll_pwait+8)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 000000000001e31c  /system/lib64/libc.so (epoll_pwait+64)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 0000000000018080  /system/lib64/libutils.so (_ZN7android6Looper9pollInnerEi+156)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 0000000000017f34  /system/lib64/libutils.so (_ZN7android6Looper8pollOnceEiPiS1_PPv+60)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 00000000000f7670  /system/lib64/libandroid_runtime.so (_ZN7android18NativeMessageQueue8pollOnceEP7_JNIEnvP8_jobjecti+48)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 00000000009dfe20  /system/framework/arm64/boot-framework.oat (Java_android_os_MessageQueue_nativePollOnce__JI+140)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at android.os.MessageQueue.nativePollOnce(Native method)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at android.os.MessageQueue.next(MessageQueue.java:323)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at android.os.Looper.loop(Looper.java:136)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at android.app.ActivityThread.main(ActivityThread.java:6682)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.reflect.Method.invoke!(Native method)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at com.android.internal.os.ZygoteInit$MethodAndArgsCaller.run(ZygoteInit.java:1520)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:1410)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "Jit thread pool worker thread 0" prio=10 tid=2 Native (still starting up)
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x0 self=0x74ce413000
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3028 nice=9 cgrp=default sched=0/0 handle=0x74d930b450
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=219 stm=38 core=3 HZ=100
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74d920d000-0x74d920f000 stackSize=1021KB
2019-02-12 15:03:50.495 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait_queue_me+0xd4/0x12c
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait+0xd8/0x1d4
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_futex+0xf0/0x16c
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000001bf6c  /system/lib64/libc.so (syscall+28)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 00000000000e725c  /system/lib64/libart.so (_ZN3art17ConditionVariable16WaitHoldingLocksEPNS_6ThreadE+160)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 0000000000465914  /system/lib64/libart.so (_ZN3art10ThreadPool7GetTaskEPNS_6ThreadE+252)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 0000000000464dd0  /system/lib64/libart.so (_ZN3art16ThreadPoolWorker3RunEv+124)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 0000000000464700  /system/lib64/libart.so (_ZN3art16ThreadPoolWorker8CallbackEPv+116)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 0000000000068258  /system/lib64/libc.so (_ZL15__pthread_startPv+196)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #06 pc 000000000001dc00  /system/lib64/libc.so (__start_thread+16)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   (no managed stack frames)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "Signal Catcher" prio=10 tid=3 WaitingInMainSignalCatcherLoop
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12c76d30 self=0x74c8936a00
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3029 nice=0 cgrp=default sched=0/0 handle=0x74d920a450
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=0 stm=0 core=5 HZ=100
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74d9110000-0x74d9112000 stackSize=1005KB
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_sigtimedwait+0xd4/0x1ac
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_rt_sigtimedwait+0xac/0xec
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000006a7d4  /system/lib64/libc.so (__rt_sigtimedwait+8)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 0000000000024d18  /system/lib64/libc.so (sigwait+64)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 000000000043e8f4  /system/lib64/libart.so (_ZN3art9SignalSet4WaitEv+48)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 000000000043e3c4  /system/lib64/libart.so (_ZN3art13SignalCatcher13WaitForSignalEPNS_6ThreadERNS_9SignalSetE+240)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 000000000043c868  /system/lib64/libart.so (_ZN3art13SignalCatcher3RunEPv+404)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 0000000000068258  /system/lib64/libc.so (_ZL15__pthread_startPv+196)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #06 pc 000000000001dc00  /system/lib64/libc.so (__start_thread+16)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   (no managed stack frames)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "JDWP" prio=10 tid=4 WaitingInMainDebuggerLoop
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12c76dc0 self=0x74ce419400
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3030 nice=0 cgrp=default sched=0/0 handle=0x74d910d450
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=5 stm=1 core=4 HZ=100
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74d9013000-0x74d9015000 stackSize=1005KB
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: poll_schedule_timeout+0x54/0xb8
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_select+0x404/0x458
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: core_sys_select+0x1e0/0x2e8
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_pselect6+0x15c/0x1f0
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000006a72c  /system/lib64/libc.so (__pselect6+8)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 0000000000023258  /system/lib64/libc.so (select+156)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 0000000000550780  /system/lib64/libart.so (_ZN3art4JDWP12JdwpAdbState15ProcessIncomingEv+340)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 000000000030279c  /system/lib64/libart.so (_ZN3art4JDWP9JdwpState3RunEv+920)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 0000000000301c70  /system/lib64/libart.so (_ZN3art4JDWPL15StartJdwpThreadEPv+48)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 0000000000068258  /system/lib64/libc.so (_ZL15__pthread_startPv+196)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #06 pc 000000000001dc00  /system/lib64/libc.so (__start_thread+16)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   (no managed stack frames)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "ReferenceQueueDaemon" prio=10 tid=6 Waiting
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12c76ee0 self=0x74d9db3c00
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3032 nice=0 cgrp=default sched=0/0 handle=0x74d8f0b450
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=4 stm=0 core=0 HZ=100
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74d8e09000-0x74d8e0b000 stackSize=1037KB
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait_queue_me+0xd4/0x12c
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait+0xd8/0x1d4
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_futex+0xf0/0x16c
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000001bf6c  /system/lib64/libc.so (syscall+28)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 00000000000e725c  /system/lib64/libart.so (_ZN3art17ConditionVariable16WaitHoldingLocksEPNS_6ThreadE+160)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 00000000003790e0  /system/lib64/libart.so (_ZN3art7Monitor4WaitEPNS_6ThreadElibNS_11ThreadStateE+660)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 0000000000000810  /system/framework/arm64/boot.oat (Java_java_lang_Object_wait__+124)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Object.wait!(Native method)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   - waiting on <0x04264508> (a java.lang.Class<java.lang.ref.ReferenceQueue>)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Daemons$ReferenceQueueDaemon.run(Daemons.java:150)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   - locked <0x04264508> (a java.lang.Class<java.lang.ref.ReferenceQueue>)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Thread.run(Thread.java:762)
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "FinalizerDaemon" prio=10 tid=7 Waiting
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12c76f70 self=0x74c8f90000
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3033 nice=0 cgrp=default sched=0/0 handle=0x74d8e06450
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=34 stm=1 core=3 HZ=100
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74d8d04000-0x74d8d06000 stackSize=1037KB
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait_queue_me+0xd4/0x12c
2019-02-12 15:03:50.496 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait+0xd8/0x1d4
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_futex+0xf0/0x16c
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000001bf6c  /system/lib64/libc.so (syscall+28)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 00000000000e725c  /system/lib64/libart.so (_ZN3art17ConditionVariable16WaitHoldingLocksEPNS_6ThreadE+160)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 00000000003790e0  /system/lib64/libart.so (_ZN3art7Monitor4WaitEPNS_6ThreadElibNS_11ThreadStateE+660)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 0000000000000980  /system/framework/arm64/boot.oat (Java_java_lang_Object_wait__JI+140)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Object.wait!(Native method)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   - waiting on <0x04893701> (a java.lang.Object)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Object.wait(Object.java:407)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:188)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   - locked <0x04893701> (a java.lang.Object)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:209)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Daemons$FinalizerDaemon.run(Daemons.java:204)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Thread.run(Thread.java:762)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "FinalizerWatchdogDaemon" prio=10 tid=8 Waiting
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12c830d0 self=0x74c8f90a00
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3034 nice=0 cgrp=default sched=0/0 handle=0x74d8d01450
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=0 stm=1 core=3 HZ=100
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74d8bff000-0x74d8c01000 stackSize=1037KB
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait_queue_me+0xd4/0x12c
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait+0xd8/0x1d4
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_futex+0xf0/0x16c
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000001bf6c  /system/lib64/libc.so (syscall+28)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 00000000000e725c  /system/lib64/libart.so (_ZN3art17ConditionVariable16WaitHoldingLocksEPNS_6ThreadE+160)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 00000000003790e0  /system/lib64/libart.so (_ZN3art7Monitor4WaitEPNS_6ThreadElibNS_11ThreadStateE+660)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 0000000000000810  /system/framework/arm64/boot.oat (Java_java_lang_Object_wait__+124)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Object.wait!(Native method)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   - waiting on <0x08f4e9a6> (a java.lang.Daemons$FinalizerWatchdogDaemon)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Daemons$FinalizerWatchdogDaemon.sleepUntilNeeded(Daemons.java:269)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   - locked <0x08f4e9a6> (a java.lang.Daemons$FinalizerWatchdogDaemon)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Daemons$FinalizerWatchdogDaemon.run(Daemons.java:249)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Thread.run(Thread.java:762)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "HeapTaskDaemon" prio=10 tid=9 Blocked
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12c83160 self=0x74c8f91400
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3035 nice=0 cgrp=default sched=0/0 handle=0x74d8bfc450
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=156 stm=19 core=7 HZ=100
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74d8afa000-0x74d8afc000 stackSize=1037KB
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait_queue_me+0xd4/0x12c
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait+0xd8/0x1d4
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_futex+0xf0/0x16c
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000001bf6c  /system/lib64/libc.so (syscall+28)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 00000000000e725c  /system/lib64/libart.so (_ZN3art17ConditionVariable16WaitHoldingLocksEPNS_6ThreadE+160)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 0000000000240ad0  /system/lib64/libart.so (_ZN3art2gc13TaskProcessor7GetTaskEPNS_6ThreadE+360)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 00000000002413ac  /system/lib64/libart.so (_ZN3art2gc13TaskProcessor11RunAllTasksEPNS_6ThreadE+92)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   - waiting to lock an unknown object
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Daemons$HeapTaskDaemon.run(Daemons.java:433)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at java.lang.Thread.run(Thread.java:762)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "Binder:3023_1" prio=10 tid=5 Native
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12c83310 self=0x74c8f91e00
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3036 nice=0 cgrp=default sched=0/0 handle=0x74d8af7450
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=366 stm=559 core=1 HZ=100
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74d89fd000-0x74d89ff000 stackSize=1005KB
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: binder_thread_read+0xd48/0xe80
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: binder_ioctl+0x3e8/0x800
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_vfs_ioctl+0x580/0x654
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_ioctl+0x5c/0x88
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000006a6e0  /system/lib64/libc.so (__ioctl+4)
2019-02-12 15:03:50.497 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 000000000001fb48  /system/lib64/libc.so (ioctl+144)
2019-02-12 15:03:50.498 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 00000000000555d4  /system/lib64/libbinder.so (_ZN7android14IPCThreadState14talkWithDriverEb+260)
2019-02-12 15:03:50.498 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 0000000000055734  /system/lib64/libbinder.so (_ZN7android14IPCThreadState20getAndExecuteCommandEv+24)
2019-02-12 15:03:50.498 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 0000000000055e80  /system/lib64/libbinder.so (_ZN7android14IPCThreadState14joinThreadPoolEb+72)
2019-02-12 15:03:50.498 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 0000000000072ec4  /system/lib64/libbinder.so (???)
2019-02-12 15:03:50.498 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #06 pc 0000000000012488  /system/lib64/libutils.so (_ZN7android6Thread11_threadLoopEPv+272)
2019-02-12 15:03:50.498 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #07 pc 00000000000a4d30  /system/lib64/libandroid_runtime.so (_ZN7android14AndroidRuntime15javaThreadShellEPv+116)
2019-02-12 15:03:50.498 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #08 pc 0000000000068258  /system/lib64/libc.so (_ZL15__pthread_startPv+196)
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12e5d700 self=0x74b73e8c00
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74b7592000-0x74b7594000 stackSize=1005KB
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 00000000002e3c78  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 00000000002df008  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 00000000002f02e4  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #08 pc 000000000001dc00  /system/lib64/libc.so (__start_thread+16)
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   (no managed stack frames)
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12e5d790 self=0x74b7f14e00
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3070 nice=0 cgrp=default sched=0/0 handle=0x74b7789450
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=14566 stm=23099 core=3 HZ=100
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_epoll_wait+0x2ac/0x36c
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_epoll_pwait+0xa8/0x124
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.503 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000006a5f4  /system/lib64/libc.so (__epoll_pwait+8)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 000000000001e31c  /system/lib64/libc.so (epoll_pwait+64)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 00000000002ea4b4  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 00000000002df008  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 00000000002f02e4  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 00000000002f01ec  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #07 pc 000000000001dc00  /system/lib64/libc.so (__start_thread+16)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   (no managed stack frames)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "signaling_threa - 3082" prio=10 tid=19 Native
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12e5d8b0 self=0x74b7f2a200
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3082 nice=0 cgrp=default sched=0/0 handle=0x74b758f450
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=11 stm=3 core=3 HZ=100
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74b7495000-0x74b7497000 stackSize=1005KB
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait_queue_me+0xd4/0x12c
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait+0xd8/0x1d4
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_futex+0xf0/0x16c
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000001bf6c  /system/lib64/libc.so (syscall+28)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 00000000000679b0  /system/lib64/libc.so (pthread_cond_wait+96)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 000000000025372c  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 00000000002e3c78  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 00000000002df008  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 00000000002f02e4  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #06 pc 00000000002f01ec  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #08 pc 000000000001dc00  /system/lib64/libc.so (__start_thread+16)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   (no managed stack frames)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "ConnectivityManager" prio=10 tid=20 Native
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12f065e0 self=0x74b7f15800
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_epoll_wait+0x2ac/0x36c
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_epoll_pwait+0xa8/0x124
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000006a5f4  /system/lib64/libc.so (__epoll_pwait+8)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 000000000001e31c  /system/lib64/libc.so (epoll_pwait+64)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 0000000000018080  /system/lib64/libutils.so (_ZN7android6Looper9pollInnerEi+156)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 0000000000017f34  /system/lib64/libutils.so (_ZN7android6Looper8pollOnceEiPiS1_PPv+60)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 00000000000f7670  /system/lib64/libandroid_runtime.so (_ZN7android18NativeMessageQueue8pollOnceEP7_JNIEnvP8_jobjecti+48)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 00000000009dfe20  /system/framework/arm64/boot-framework.oat (Java_android_os_MessageQueue_nativePollOnce__JI+140)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at android.os.MessageQueue.nativePollOnce(Native method)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at android.os.Looper.loop(Looper.java:136)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at android.os.HandlerThread.run(HandlerThread.java:61)
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "AudioTrack" prio=10 tid=25 Native
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12c761f0 self=0x74b6e50600
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74a8805000-0x74a8807000 stackSize=1005KB
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait_queue_me+0xd4/0x12c
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait+0xd8/0x1d4
2019-02-12 15:03:50.504 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.505 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_futex+0xf0/0x16c
2019-02-12 15:03:50.505 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000001bf70  /system/lib64/libc.so (syscall+32)
2019-02-12 15:03:50.505 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 0000000000067a90  /system/lib64/libc.so (pthread_cond_timedwait+152)
2019-02-12 15:03:50.505 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #02 pc 00000000000ce5b0  /system/lib64/libmedia.so (_ZN7android10AudioTrack16AudioTrackThread10threadLoopEv+384)
2019-02-12 15:03:50.505 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 00000000000a4d30  /system/lib64/libandroid_runtime.so (_ZN7android14AndroidRuntime15javaThreadShellEPv+116)
2019-02-12 15:03:50.505 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 0000000000068258  /system/lib64/libc.so (_ZL15__pthread_startPv+196)
2019-02-12 15:03:50.505 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #06 pc 000000000001dc00  /system/lib64/libc.so (__start_thread+16)
2019-02-12 15:03:50.505 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "AudioTrackJavaThread" prio=10 tid=27 Native
2019-02-12 15:03:50.505 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12c83dc0 self=0x74b9497a00
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3137 nice=-19 cgrp=default sched=0/0 handle=0x74a82fd450
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=26702 stm=1464 core=2 HZ=100
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74a81fb000-0x74a81fd000 stackSize=1037KB
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait+0xd8/0x1d4
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_futex+0xf0/0x16c
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 00000000000e725c  /system/lib64/libart.so (_ZN3art17ConditionVariable16WaitHoldingLocksEPNS_6ThreadE+160)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 0000000000545ccc  /system/lib64/libart.so (_ZN3art12JniMethodEndEjPNS_6ThreadE+28)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 00000000006b8d70  /system/framework/arm64/boot-framework.oat (Java_android_media_AudioTrack_native_1write_1native_1bytes__Ljava_lang_Object_2IIIZ+204)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at android.media.AudioTrack.native_write_native_bytes(Native method)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at org.webrtc.audio.WebRtcAudioTrack$AudioTrackThread.writeBytes(WebRtcAudioTrack.java:151)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at org.webrtc.audio.WebRtcAudioTrack$AudioTrackThread.run(WebRtcAudioTrack.java:115)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "AudioRecord" prio=10 tid=28 Native
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12c83820 self=0x74a9573a00
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3138 nice=-16 cgrp=default sched=0/0 handle=0x74a81f8450
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=0 stm=0 core=7 HZ=100
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74a80fe000-0x74a8100000 stackSize=1005KB
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait_queue_me+0xd4/0x12c
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait+0xd8/0x1d4
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000001bf70  /system/lib64/libc.so (syscall+32)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 0000000000067a90  /system/lib64/libc.so (pthread_cond_timedwait+152)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 00000000000a4d30  /system/lib64/libandroid_runtime.so (_ZN7android14AndroidRuntime15javaThreadShellEPv+116)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 0000000000068258  /system/lib64/libc.so (_ZL15__pthread_startPv+196)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #06 pc 000000000001dc00  /system/lib64/libc.so (__start_thread+16)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   (no managed stack frames)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "AudioRecordJavaThread" prio=10 tid=29 Native
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12cb9310 self=0x74b9498400
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3145 nice=-19 cgrp=default sched=0/0 handle=0x74a7dff450
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=24306 stm=6715 core=1 HZ=100
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74a7cfd000-0x74a7cff000 stackSize=1037KB
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_futex+0xf0/0x16c
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000001bf6c  /system/lib64/libc.so (syscall+28)
2019-02-12 15:03:50.506 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 00000000000e725c  /system/lib64/libart.so (_ZN3art17ConditionVariable16WaitHoldingLocksEPNS_6ThreadE+160)
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12ce3c10 self=0x74a8f26200
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3147 nice=-2 cgrp=default sched=0/0 handle=0x74a7bf5450
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=2325 stm=1725 core=0 HZ=100
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74a7afb000-0x74a7afd000 stackSize=1005KB
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait_queue_me+0xd4/0x12c
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait+0xd8/0x1d4
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_futex+0xf0/0x16c
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000001bf70  /system/lib64/libc.so (syscall+32)
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 0000000000067a90  /system/lib64/libc.so (pthread_cond_timedwait+152)
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 0000000000012488  /system/lib64/libutils.so (_ZN7android6Thread11_threadLoopEPv+272)
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #05 pc 0000000000068258  /system/lib64/libc.so (_ZL15__pthread_startPv+196)
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #06 pc 000000000001dc00  /system/lib64/libc.so (__start_thread+16)
2019-02-12 15:03:50.508 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   (no managed stack frames)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "AndroidVideoDecoder.outputThread" prio=10 tid=33 Native
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12ce3b80 self=0x74a8ef8200
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3169 nice=0 cgrp=default sched=0/0 handle=0x74a727b450
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=3685 stm=805 core=2 HZ=100
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74a7179000-0x74a717b000 stackSize=1037KB
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait_queue_me+0xd4/0x12c
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: futex_wait+0xd8/0x1d4
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: do_futex+0xc8/0x8d4
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_futex+0xf0/0x16c
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000001bf6c  /system/lib64/libc.so (syscall+28)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 00000000000e725c  /system/lib64/libart.so (_ZN3art17ConditionVariable16WaitHoldingLocksEPNS_6ThreadE+160)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 0000000000545ccc  /system/lib64/libart.so (_ZN3art12JniMethodEndEjPNS_6ThreadE+28)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at android.media.MediaCodec.native_dequeueOutputBuffer(Native method)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at android.media.MediaCodec.dequeueOutputBuffer(MediaCodec.java:2568)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at org.webrtc.MediaCodecWrapperFactoryImpl$MediaCodecWrapperImpl.dequeueOutputBuffer(MediaCodecWrapperFactoryImpl.java:73)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   at org.webrtc.AndroidVideoDecoder.deliverDecodedFrame(AndroidVideoDecoder.java:390)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] "IncomingVideoSt - 3140" prio=10 tid=34 Native
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | group="" sCount=1 dsCount=0 obj=0x12ce34c0 self=0x74a96ace00
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | sysTid=3140 nice=0 cgrp=default sched=0/0 handle=0x74a8c01450
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | state=S schedstat=( 0 0 0 ) utm=1519 stm=398 core=3 HZ=100
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | stack=0x74a8b03000-0x74a8b05000 stackSize=1021KB
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   | held mutexes=
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: SyS_epoll_pwait+0xa8/0x124
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   kernel: cpu_switch_to+0x48/0x4c
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #00 pc 000000000006a5f4  /system/lib64/libc.so (__epoll_pwait+8)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #01 pc 000000000001e31c  /system/lib64/libc.so (epoll_pwait+64)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #03 pc 00000000002f427c  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #04 pc 00000000002538f8  /data/app/com.miku.mikucrashtest-2/lib/arm64/libjingle_peerconnection_so.so (???)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   native: #06 pc 0000000000068258  /system/lib64/libc.so (_ZL15__pthread_startPv+196)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404]   (no managed stack frames)
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.509 3023-3148/com.miku.mikucrashtest A/art: art/runtime/runtime.cc:404] 
2019-02-12 15:03:50.515 3023-3148/com.miku.mikucrashtest A/libc: Fatal signal 6 (SIGABRT), code -6 in tid 3148 (JNISurfaceTextu)
```
