#EmailServer.py
#Taylor Farmer
#When calling this script, do it in the format: python EmailServer.py UserEmailAddressHere UserPasswordHere

import smtplib
import sys
from email.MIMEMultipart import MIMEMultipart
from email.MIMEText import MIMEText

    #Arguments from cmd
first_arg = sys.argv[1]
second_arg = sys.argv[2]

    #To and From addresses
fromaddr = "RehabStarProject@gmail.com"
toaddr = first_arg

    #Making the msg object that was imported using MIME.
msg = MIMEMultipart()
msg['From'] = fromaddr
msg['To'] = toaddr

    #Email Subject line
msg['Subject'] = "Forgot Password"

    #Body of email.
body = "Your password is: " + second_arg
msg.attach(MIMEText(body, 'plain'))

    #Start connection to gmail.
server = smtplib.SMTP('smtp.gmail.com', 587)
server.starttls()

    #Login to gmail.
server.login(fromaddr, "X@vier19")

    #Change the msg objet to something gmail can understand, i.e. a string.
text = msg.as_string()

    #Send email.
server.sendmail(fromaddr, toaddr, text)

    #Print "Finished in CMD"
print ("Finished")

    #Close connection with gmail.
server.quit()
