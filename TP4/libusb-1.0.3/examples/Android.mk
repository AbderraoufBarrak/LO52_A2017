LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES := lsusb.c
LOCAL_C_INCLUDES := device/utbm/lo52/libusb

LOCAL_MODULE := lsusb
LOCAL_SHARED_LIBRARIES := libc libusb

include $(BUILD_EXECUTABLE)
