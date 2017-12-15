$(call inherit-product, device/hikey/hikey/device-hikey.mk)

PRODUCT_PACKAGES += libusb

DEVICE_PACKAGE_OVERLAYS := \		
	device/utbm/lo52/overlay
PRODUCT_PROPERTY_OVERRIDES += \		
	ro.hw = lo52
PRODUCT_PROPOERTY_OVERRIDES += \ net.dns1= 8.8.8.8
PRODUCT_PROPOERTY_OVERRIDES += \ net.dns2= 4.4.4.4
PRODUCT_NAME := lo52			
PRODUCT_DEVICE := lo52		
PRODUCT_BRAND := Android			
PRODUCT_MODEL := LO52 Utbm		
include $(call all-subdir-makefiles)		
