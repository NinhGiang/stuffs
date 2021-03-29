from tkinter import *
from tkinter import messagebox
import re
import thanh_toan
# Setup
app = Tk()
app.title("Registeration/login form")
app.configure(background="#181818")
app.geometry("300x300")

# Variables
login_name = StringVar()
login_pass = StringVar()
registeration_name = StringVar()
registeration_pass = StringVar()
managerlogin_name = StringVar()
managerlogin_pass = StringVar()
branch = 'Branch Phan Van Tri'
def registeration_check():
    rx = re.compile(f"name: {registeration_name.get()}")
    file = open('result.txt', 'a+')
    if rx.match(file.read()):
        messagebox.showerror(title="Duplicated name", message=f"The name requested, {registeration_name.get()} is already included in the database.")
        return False
    else:
        return True

def registeration_submit():
    if registeration_check():
        file = open("result.txt", "a+")
        file.write(f"name: {registeration_name.get()}, pass: {registeration_pass.get()}\n")
        file.close()
    else:
        pass
def login_check():  
    rx = re.compile(f"name: {login_name.get()}")
    file = open("result.txt")
    if (rx.match(file.read())):
        rx2 = re.compile(f"name: {login_name.get()}, pass: {login_pass.get()}")
        messagebox.showinfo(title="Success", message=f"Login success.")
        thanh_toan.create()
    else:
        messagebox.showerror(title="Unsuccess", message=f"Password or username wrong.")
def login_submit():
    if login_check():
        messagebox.showinfo(title="Success", message="Success!")
    else:
        pass
def create():
    # Title
    login_title = Label(app, text="Login form", background="#181818", foreground="#e8e8e8", font=("Times New Roman", 22))
    login_title.grid(row=0, column=0, columnspan=2)
    # Name label-entry
    login_label_name = Label(app, text="Name: ", background="#181818", foreground="#e8e8e8", font=("Calibri", 16))
    login_label_name.grid(row=1, column=0)
    login_entry_name = Entry(app, width=15, background="#e8e8e8", foreground="#181818", font=("Calibri", 16), textvariable=login_name)
    login_entry_name.grid(row=1, column=1)
    # Password label-entry
    login_label_pass = Label(app, text="Pass: ", background="#181818", foreground="#e8e8e8", font=("Calibri", 16))
    login_label_pass.grid(row=2, column=0)
    login_entry_pass = Entry(app, width=15, show="*", background="#e8e8e8", foreground="#181818", font=("Calibri", 16), textvariable=login_pass)
    login_entry_pass.grid(row=2, column=1)
    # Submit button
    btn_login_submit = Button(app, text="Submit", background="#181818", foreground="#e8e8e8", font=("Calibri", 16), command=login_submit)
    btn_login_submit.grid(row=3, column=0, columnspan=2)
    # Title
    registeration_title = Label(app, text="Registeration form", background="#181818", foreground="#e8e8e8", font=("Times New Roman", 22))
    registeration_title.grid(row=4, column=0, columnspan=2)
    # Name label-entry
    registeration_label_name = Label(app, text="Name: ", background="#181818", foreground="#e8e8e8", font=("Calibri", 16))
    registeration_label_name.grid(row=5, column=0)
    registeration_entry_name = Entry(app, width=15, background="#e8e8e8", foreground="#181818", font=("Calibri", 16), textvariable=registeration_name)
    registeration_entry_name.grid(row=5, column=1)
    # Password label-entry
    registeration_label_pass = Label(app, text="Pass: ", background="#181818", foreground="#e8e8e8", font=("Calibri", 16))
    registeration_label_pass.grid(row=6, column=0)
    registeration_entry_pass = Entry(app, width=15, show="*", background="#e8e8e8", foreground="#181818", font=("Calibri", 16), textvariable=registeration_pass)
    registeration_entry_pass.grid(row=6, column=1)
    # Submit button
    btn_registeration_submit = Button(app, text="Submit", background="#181818", foreground="#e8e8e8", font=("Calibri", 16), command=registeration_submit)
    btn_registeration_submit.grid(row=7, column=0, columnspan=2)

    # Title
    login_title = Label(app, text="Manager login form", background="#181818", foreground="#e8e8e8", font=("Times New Roman", 22))
    login_title.grid(row=0, column=22, columnspan=2)
    # Name label-entry
    login_label_name = Label(app, text="Name: ", background="#181818", foreground="#e8e8e8", font=("Calibri", 16))
    login_label_name.grid(row=1, column=22, columnspan=1)
    login_entry_name = Entry(app, width=15, background="#e8e8e8", foreground="#181818", font=("Calibri", 16), textvariable=managerlogin_name)
    login_entry_name.grid(row=1, column=23)
    # Password label-entry
    login_label_pass = Label(app, text="Pass: ", background="#181818", foreground="#e8e8e8", font=("Calibri", 16))
    login_label_pass.grid(row=2, column=22, columnspan=1)
    login_entry_pass = Entry(app, width=15, show="*", background="#e8e8e8", foreground="#181818", font=("Calibri", 16), textvariable=managerlogin_pass)
    login_entry_pass.grid(row=2, column=23)
    # Submit button
    btn_login_submit_2 = Button(app, text="Submit", background="#181818", foreground="#e8e8e8", font=("Calibri", 16), command=login_submit)
    btn_login_submit_2.grid(row=3, column=23, columnspan=2)
    # Other
    app.mainloop()