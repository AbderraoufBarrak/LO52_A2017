$(call inherit-product, device/utbm/lo52/...)

PRODUCT_PACKAGES += \
    lo52

DEVICE_PACKAGE_OVERLAYS := \
    device/utbm/lo52/DEVICE_PACKAGE_OVERLAYS

PRODUCT_PROPERTY_OVERRIDES += \
    ro.hw ) lo52

PRODUCT_NAME := lo52
PRODUCT_DEVICE := lo52
PRODUCT_BRAND := Android
PRODUCT_MODEL := LO52_UTBM

include $(call all-subdir-makefiles)
