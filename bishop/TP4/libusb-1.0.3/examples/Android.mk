LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES := examples/lsusb.c

LOCAL_MODULE := lsusb
LOCAL_MODULE_TAGS := optional

LOCAL_C_INCLUDES += libusb-1.0.3/
LOCAL_SHARED_LIBRARIES := libc libusb

include $(BUILD_EXECUTABLE)