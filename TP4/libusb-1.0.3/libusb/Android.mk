LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := libusb-lib
LOCAL_MODULE_TAGS := user
LOCAL_SRC_FILES := core.c descriptor.c io.c sync.c os/linux_usbfs.c
LOCAL_C_INCLUDES += device/vendor/libusb/os device/vendor/libusb/include

include $(BUILD_SHARED_LIBRARY)


