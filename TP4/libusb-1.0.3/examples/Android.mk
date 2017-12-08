LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_SRC_FILES := examples/lsusb.c
LOCAL_MODULE := lsusb
LOCAL_C_INCLUDES += device/vendor/libusb/
LOCAL_SHARED_LIBRARIES := libc libusb
include $(BUILD_EXECUTABLE)
