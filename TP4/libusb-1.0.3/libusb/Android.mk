LOCAL_PATH :=  $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := libusb

LOCAL_SRC_FILES := \
	core.c \
	descriptor.c \
	io.c \
	sync.c \
	linux_usbfs.c
	
LOCAL_C_INCLUDES += \
	$(LOCAL_PATH)//os
	
include $(BUILD_HOST_STATIC_LIBRARY)