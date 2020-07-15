# Copyright (C) 2019 Emcraft Systems
SUMMARY = "Deploy uuu to images folder"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://LICENSE;md5=38ec0c18112e9a92cffc4951661e85a5"
DEPENDS = "libusb1-native libzip-native"

SRC_URI = "git://github.com/codeauroraforum/mfgtools.git;protocol=https;branch=master"
SRCREV = "uuu_1.3.82"
S = "${WORKDIR}/git"

inherit cmake native deploy

do_deploy () {
    install -m 0755 ${B}/uuu/uuu ${DEPLOYDIR}
}
addtask deploy after do_compile
