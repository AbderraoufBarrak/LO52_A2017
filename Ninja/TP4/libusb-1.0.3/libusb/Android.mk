LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_SRC_FILES := core.c \
                   descriptor.c \
                   io.c \ 
                   sync.c 
LOCAL_C_INCLUDES += libusb \
                    libusbi
LOCAL_MODULE := libusb_lib
LOCAL_MODULE_TAGS := optional
include $(BUILD_SHARED_LIBRARY)
