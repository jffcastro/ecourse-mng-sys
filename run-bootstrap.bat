REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET SEM4PI_CP=base.app.bootstrap\target\base.app.bootstrap-1.4.0-SNAPSHOT.jar;base.app.bootstrap\target\dependency\*;

REM call the java VM
java -cp %SEM4PI_CP% application.base.app.bootstrap.BaseBootstrap -bootstrap:demo -smoke:basic -smoke:e2e