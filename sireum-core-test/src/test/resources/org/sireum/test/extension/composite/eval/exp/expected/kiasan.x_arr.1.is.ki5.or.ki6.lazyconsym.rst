Exp: @@x_arr[1ii]

State: Before

* Global Variables

  * @@x_arr = KArray#0

    * min=i1; length=i2; max=i3

    * [i4] = i5

  * @@y = i4

* Path Conditions

  * i1 <= i3

  * 0 <= i2

  * i2 == i3 - i1 + 1

  * 1 <= i2

----

+---------------------------------+---------------------------------+
|                                 |                                 |
| State: After                    | State: After (Semi-Concrete)    |
|                                 |                                 |
| * Global Variables              | * Global Variables              |
|                                 |                                 |
|   * @@x_arr = KArray#0          |   * @@x_arr = KArray#0          |
|                                 |                                 |
|     * min=i1; length=i2; max=i3 |     * min=-1; length=2; max=0   |
|                                 |                                 |
|     * [1] = i6                  |     * [1] = i6                  |
|                                 |                                 |
|     * [i4] = i5                 |     * [5] = i5                  |
|                                 |                                 |
|   * @@y = i4                    |   * @@y = 5                     |
|                                 |                                 |
| * Path Conditions               | * Path Conditions               |
|                                 |                                 |
|   * i1 <= i3                    |   * -1 <= 0                     |
|                                 |                                 |
|   * 0 <= i2                     |   * 0 <= 2                      |
|                                 |                                 |
|   * i2 == i3 - i1 + 1           |   * 2 == 0 - -1 + 1             |
|                                 |                                 |
|   * 1 <= i2                     |   * 1 <= 2                      |
|                                 |                                 |
|   * 2 <= i2                     |   * 2 <= 2                      |
|                                 |                                 |
|   * 1 != i4                     |   * 1 != 5                      |
|                                 |                                 |
| * Result: i6                    | * Result: i6                    |
|                                 |                                 |
+---------------------------------+---------------------------------+

----

+---------------------------------+---------------------------------+
|                                 |                                 |
| State: After                    | State: After (Semi-Concrete)    |
|                                 |                                 |
| * Global Variables              | * Global Variables              |
|                                 |                                 |
|   * @@x_arr = KArray#0          |   * @@x_arr = KArray#0          |
|                                 |                                 |
|     * min=i1; length=i2; max=i3 |     * min=0; length=1; max=0    |
|                                 |                                 |
|     * [1] = i5                  |     * [1] = i5                  |
|                                 |                                 |
|   * @@y = i4                    |   * @@y = 1                     |
|                                 |                                 |
| * Path Conditions               | * Path Conditions               |
|                                 |                                 |
|   * i1 <= i3                    |   * 0 <= 0                      |
|                                 |                                 |
|   * 0 <= i2                     |   * 0 <= 1                      |
|                                 |                                 |
|   * i2 == i3 - i1 + 1           |   * 1 == 0 - 0 + 1              |
|                                 |                                 |
|   * 1 <= i2                     |   * 1 <= 1                      |
|                                 |                                 |
|   * 1 == i4                     |   * 1 == 1                      |
|                                 |                                 |
| * Result: i5                    | * Result: i5                    |
|                                 |                                 |
+---------------------------------+---------------------------------+
