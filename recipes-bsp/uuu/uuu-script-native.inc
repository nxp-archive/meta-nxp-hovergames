# Copyright (C) 2019 Emcraft Systems
SUMMARY = "Deploy customized uuu scripts to images folder"
LICENSE = "BSD"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/BSD;md5=3775480a712fc46a69647678acb234cb"
DEPENDS = "uuu-native"

inherit native deploy

SRC_URI = "file://uuu.script"
IMAGES ?= ""
SD_NUM ?= ""
EMMC_NUM ?= ""

create_script() {
    image=$1
    dev_name=$2
    dev_number=$3
    cp ${WORKDIR}/uuu.script           ${B}/scripts/uuu.$image.$dev_name
    sed -i -e "s/MACHINE/${MACHINE}/g" ${B}/scripts/uuu.$image.$dev_name
    sed -i -e "s/IMAGE/$image/g"       ${B}/scripts/uuu.$image.$dev_name
    sed -i -e "s/MMCDEV/$dev_number/g" ${B}/scripts/uuu.$image.$dev_name
}

do_compile() {
    mkdir ${B}/scripts
    for image in ${IMAGES}; do
        if [ ! -z ${SD_NUM} ]; then
            create_script $image sd ${SD_NUM}
        fi
        if [ ! -z ${EMMC_NUM} ]; then
            create_script $image emmc ${EMMC_NUM}
        fi
    done
}

do_deploy () {
    for uuu_script in ${B}/scripts/*; do
        install -m 0644 $uuu_script ${DEPLOYDIR}
    done
}
addtask deploy after do_compile
