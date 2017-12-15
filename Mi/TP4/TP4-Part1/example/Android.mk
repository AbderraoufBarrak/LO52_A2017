LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES := lsusb.c

LOCAL_MODULE := lsusb

LOCAL_C_INCLUDES +=\
		../

LOCAL_SHARED_LIBRAIRIES := libusb libc

include $(BUILD_EXECUTABLE)
