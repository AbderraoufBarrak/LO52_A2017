LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := lsusb
LOCAL_SRC_FILES:= lsusb.c
LOCAL_C_INCLUDES += external/libusb-1.0.3/
LOCAL_SHARED_LIBRARIES := libc libusb

include $(BUILD_EXECUTABLE)

