LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := lsusb
LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := lsusb.c

LOCAL_C_INCLUDES += $(LOCAL_PATH) \
					$(LOCAL_PATH)/../libusb \
					$(LOCAL_PATH)/../libusb/os \
					
LOCAL_SHARED_LIBRARIES := libusb

include $(BUILD_EXECUTABLE)