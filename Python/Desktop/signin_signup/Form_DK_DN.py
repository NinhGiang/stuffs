from tkinter import *
from tkinter import messagebox
# tạo cửa sổ
window = Tk()
window.title('Đăng kí + Đăng nhập')
window.configure(background='pink')
window.geometry('300x300')
# khai báo biến
var_user = StringVar()
var_pass = StringVar()
var_username = StringVar()
var_password = StringVar()
# tạo hàm
def dang_ki():
    file = open('dk_dn.txt', 'a+')
    line = var_user.get() + ' + ' + var_pass.get() + '\n'
    file.write(line)
    file.close()
    messagebox.showinfo(window, message='Register sucessfully')
def dang_nhap():
    login_thanhcong = False
    file_name = 'dk_dn.txt'
    file = open(filename)
    login_info = var_user.get() + " + " + var_pass.get() + "\n"
    for line in file.readlines():
        if login_info == line:
            login_thanhcong = True

'''def dang_nhap():
    success_login = False
    file_name = 'dk_dn.txt'
    lines = []
    with open(file_name) as file:
        for line in file:
            lines.append(line)
    for i in range(len(lines)):
        if (var_username.get() + ' + ' + var_password.get() + '\n') == lines[i]:
            success_login = True
    if success_login:
        messagebox.showinfo(window, message='Login sucessfully')
    else:
        messagebox.showwarning(window, message='Invalid username/password')
    return None'''
# tạo title
lbl_title = Label(window, text='FORM ĐĂNG KÍ', font=('Arial', 16))
lbl_title.grid(row = 0, column = 0, columnspan = 2)
# tạo label name
lbl_name = Label(window, text='Name: ')
lbl_name.grid(row = 1, column = 0)
# tạo ô ghi name
txt_name = Entry(window, width=20, textvariable=var_user)
txt_name.grid(row = 1, column = 1)
# tạo label pass
lbl_pass = Label(window, text='Pass: ')
lbl_pass.grid(row = 2, column = 0)
# tạo ô ghi pass
txt_pass = Entry(window, width=20, show='*', textvariable=var_pass)
txt_pass.grid(row = 2, column = 1)
# tạo nút submit
btn_register = Button(window, text='Register', command=dang_ki)
btn_register.grid(row = 3, column = 2)

# tạo title đăng nhập
lbl_title1 = Label(window, text='FORM ĐĂNG NHẬP', font=('Arial', 16))
lbl_title1.grid(row = 4, column = 0, columnspan = 2)
# tạo label username
lbl_username = Label(window, text='Username: ')
lbl_username.grid(row = 5, column = 0)
# tạo ô ghi username
txt_username = Entry(window, width=20, textvariable=var_username)
txt_username.grid(row = 5, column = 1)
# tạo label password
lbl_password = Label(window, text='Password: ')
lbl_password.grid(row = 6, column = 0)
# tạo ô ghi password
txt_pass = Entry(window, width=20, show='*', textvariable=var_password)
txt_pass.grid(row = 6, column = 1)
# tạo nút submit
btn_login = Button(window, text='Login', command=dang_nhap)
btn_login.grid(row = 7, column = 2)
window.mainloop()
