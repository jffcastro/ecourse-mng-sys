#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export SET SEM4PI_CP=base.app.user.console\target\base.app.user.console-1.4.0-SNAPSHOT.jar;base.app.user.console\target\dependency\*;

#REM call the java VM
java -cp %SEM4PI_CP% application.base.app.user.console.BaseUserApp