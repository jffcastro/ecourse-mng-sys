REM set the class path,
REM assumes the build was executed with maven copy-dependencies
SET SEM4PI_CP=base.app.sharedBoardApp\target\classes;base.app.sharedBoardApp\target\dependency\abstract-ui.jar;base.app.sharedBoardApp\target\dependency\*;

REM Set the argument
SET ARGUMENT=127.0.0.1

REM call the java VM
java -cp %SEM4PI_CP% eapli.SharedBoardClient %ARGUMENT%
