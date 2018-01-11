$(call inherit_product, device/linaro/hikey/hikey.mk)

PRODUCT_PACKAGES += libsub_lib
DEVICE_PACKAGE_OVERLAYS := device/utbm/lo52/overlay
PRODUCT_PROPERTY_OVERRIDES += \
    ro.hw = lo52\
    net.dns1 = 8.8.8.8\
    net.dn2 = 4.4.4.4
PRODUCT_NAME := lo52
PRODUCT_DEVICE:= lo52
PRODUCT_BRAND:= Android
PRODUCT_MODEL:= Android


include $ (call-subdir-makefiles)
  
