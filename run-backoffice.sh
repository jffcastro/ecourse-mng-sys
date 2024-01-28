#!/usr/bin/env bash

#REM set the class path,
#REM assumes the build was executed with maven copy-dependencies
export SEM4PI_CP=base.app.backoffice.console\target\base.app.backoffice.console-1.4.0-SNAPSHOT.jar;base.app.backoffice.console\target\dependency\*;

#REM call the java VM
java -cp %SEM4PI_CP% application.base.app.backoffice.console.BaseBackoffice
