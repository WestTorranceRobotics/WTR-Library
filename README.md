# WTR Library Documentation for Reusable Code

Additional Notes:

    public LoaderAndIntakeGroup(Intake intake, Loader loader) {
        super(new SetIntakePower(intake, 1), new SeeBallRunBelt(loader));   
    }

NOTE:  Consider using this command inline, rather than writing a subclass.  For more
  information, see:
  https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
