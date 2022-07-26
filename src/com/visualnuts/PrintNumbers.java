package com.visualnuts;

public class PrintNumbers
{
    public void execute(Integer start, Integer finish)
    {
        try
        {
            if (start != null && finish != null)
            {
                if (start > 0 && finish > 0)
                {
                    if (finish > start)
                    {
                        print("\nStarting application from smallest to largest!");

                        for (int i = start; i <= finish; i++)
                        {
                            if (i % 3 == 0 && i % 5 == 0)
                            {
                                print("Step: Visual Nuts");
                            } else if (i % 3 == 0)
                            {
                                print("Step: Visual");
                            } else if (i % 5 == 0)
                            {
                                print("Step: Nuts");
                            } else
                            {
                                print("Step: " + i);
                            }
                        }

                    } else
                    {
                        print("\nStarting application from largest to smallest!");

                        for (int i = start; i >= finish; i--)
                        {
                            if (i % 3 == 0 && i % 5 == 0)
                            {
                                print("Step: Visual Nuts");
                            } else if (i % 3 == 0)
                            {
                                print("Step: Visual");
                            } else if (i % 5 == 0)
                            {
                                print("Step: Nuts");
                            } else
                            {
                                print("Step: " + i);
                            }
                        }
                    }
                } else
                {
                    print("Numbers less than zero: " + start + " and " + finish);
                }

            } else
            {
                print("Invalid numbers: " + start + " and " + finish);
            }

        } catch (Exception e)
        {
            print("A runtime error occurred: " + e.getMessage());
        }

    }

    private void print(String msg)
    {
        System.out.println(msg);
    }
}
