This folder contains the template to start the challenge in Chapter 12 ( A Responsive DialogFragment ).


The classes involved are:
 - DatePickerFragment and DatePickerActivity
 - SingleFragmentActivity (for the second part of the challenge)

Please note that when pushing the OK button in the DatePickerFragment, the fragment won't close (not implemented).

In addition, there is no difference between tablet and smartphones: there is only code to sendResult to the fragment/activity. 
However, this would need to be set in the CrimeFragment, mDateButton.setOnClickListener. I just didn't know how to tell the difference between tablets and smartphone. However, if you manually set the fragment target for testing purposes, you will see the right part of the sendResult is being called on the tablet.
