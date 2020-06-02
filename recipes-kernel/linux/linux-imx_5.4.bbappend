FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

SRC_URI += " \
    file://0001-Add-NavQ-device-trees.patch \
    file://0001-Add-OV5645-driver.patch \
"
