# WTR Library Documentation for Reusable Code
<p align="center"> <img src="https://i.imgur.com/fWZt6BF.png" alt="WTR Logo"/> </p>
This is the repository for reusuable code from the 2020 Season.

## Additional Notes:

```
    public LoaderAndIntakeGroup(Intake intake, Loader loader) {
        super(new SetIntakePower(intake, 1), new SeeBallRunBelt(loader));   
    }
```
- Creates a command group.
    NOTE:  Consider using this command inline, rather than writing a subclass.  For more
    information, see:
    https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
