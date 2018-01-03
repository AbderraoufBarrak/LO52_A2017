LOCAL_PATH := ($call my-dir)
include $(CLEAR_VARS)

LOCAL_SRC_FILES := core.c \
		os/linux_usbfs.c \
		io.c \
		descriptor.c \
		sync.c

LOCAL_MODULE := libusb
LOCAL_MODULE_TAGS := optional

LOCAL_C_INCLUDES += libusb-1.0.3/libusb/ \
		libusb-1.0.3/libusb/os/

include (BUILD_SHARED_LIBRARY)
