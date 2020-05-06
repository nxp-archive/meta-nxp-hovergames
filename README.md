i.MX Linux Yocto Project BSP for HoverGames
===========================================

Install the `repo` utility
--------------------------

To get the BSP you need to have `repo` installed.

```
$ mkdir ~/bin
$ curl https://storage.googleapis.com/git-repo-downloads/repo  > ~/bin/repo
$ chmod a+x ~/bin/repo
$ PATH=${PATH}:~/bin
```

Download the Yocto Project BSP
------------------------------

```
$ mkdir hovergames
$ cd hovergames
$ repo init -u ssh://bitbucket.sw.nxp.com/imx/imx-manifest -b linux-zeus-internal -m hovergames.xml
$ repo sync
```

Create a new build folder
-------------------------

If you want to create a new build folder that includes the internal layer meta-fsl-mpu-internal:
```
$ MACHINE=imx8mmlpddr4evk DISTRO=fsl-imx-internal-xwayland source fsl-setup-internal-build.sh -b build
```

Use an existing build folder
----------------------------

If you want to build in an existing build folder:

```
$ cd hovergames
$ source setup-environment build
```

Add hovergames layer to build configuration
-------------------------------------------

```
$ echo "BBLAYERS += \"\${BSPDIR}/sources/meta-nxp-hovergames\"" >> conf/bblayers.conf 
```

Build the image
---------------

```
$ bitbake imx-image-hovergames
```
