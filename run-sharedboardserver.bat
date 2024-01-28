REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET SEM4PI_CP=base.app.sharedBoardServer\target\classes;base.app.sharedBoardServer\target\dependency\*;

REM call the java VM
java -cp %SEM4PI_CP% eapli.SharedBoardServer
