# CalGames 2021

Robot code for calgames.

## Joystick Mappings

### Driver (port 4)

| Button/Stick | Purpose                     |
| ------------ | --------------------------- |
| Left Stick   | Drivetrain forward/backward |
| Right Stick  | Drivetrain turning          |
| A            | Intake arm down             |
| X            | Intake arm up               |
| LB           | Rollers on                  |
| RB           | Rollers off                 |

### Shooter (port 5)

| Button/Stick | Purpose            |
| ------------ | ------------------ |
| B            | Ramp up flywheel   |
| Y            | Ramp down flywheel |
| RT           | Run hopper         |
| X            | Reverse hopper     |

## Limelight Network Tables Values
| Value | Meaning                                                                      |
| ----- | ---------------------------------------------------------------------------- |
| tv    | Whether the limelight has any valid targets (0 or 1)                         |
| tx    | Horizontal Offset From Crosshair To Target (LL1: -27 degrees to 27 degrees   | LL2: -29.8 to 29.8 |       | degrees | ) |
| ty    | Vertical Offset From Crosshair To Target (LL1: -20.5 degrees to 20.5 degrees | LL2: -24.85 to     | 24.85 | degrees | ) |
| ta    | Target Area (0% of image to 100% of image)                                   |

## Authors:

- Satvik Reddy
- Luke Phillips

Copyright (c) 2021, Aragon Robotics
