from tkinter import *
phep_tinh = ""
def nhan_phim(ki_tu):
    global phep_tinh
    phep_tinh += str(ki_tu)
    hien_thi.set(phep_tinh)
    thanh_hien_thi.configure(text=hien_thi.get())
def nhan_bang():
    global phep_tinh
    try:
        dap_so = str(eval(phep_tinh))
        hien_thi.set(dap_so)
        phep_tinh = ""
    except:
        hien_thi.set("error")
        phep_tinh = ""
    thanh_hien_thi.configure(text=hien_thi.get())
def reset():
    global phep_tinh
    phep_tinh = ""
    hien_thi.set("")
    thanh_hien_thi.configure(text=hien_thi.get())

may_tinh = Tk()
may_tinh.configure(background="lightpink")
may_tinh.title("Máy tính tui tự làm")
may_tinh.geometry('270x150')
hien_thi = StringVar()
thanh_hien_thi = Label(may_tinh, text="Nhap")
#thanh_hien_thi = Entry(may_tinh, textvariable=hien_thi)
thanh_hien_thi.grid(columnspan=4, ipadx=70)
hien_thi.set("Nhập phép tính...")
btn_1 = Button(may_tinh, text=' 1 ', fg='black', bg='violet', height=1, width=7,
               command=lambda: nhan_phim('1'))
btn_1.grid(row=2, column=0)
btn_2 = Button(may_tinh, text=' 2 ', fg='black', bg='violet', height=1, width=7,
               command=lambda: nhan_phim('2'))
btn_2.grid(row=2, column=1)
btn_3 = Button(may_tinh, text=' 3 ', fg='black', bg='violet', height=1, width=7,
               command=lambda: nhan_phim('3'))
btn_3.grid(row=2, column=2)
btn_4 = Button(may_tinh, text=' 4 ', fg='black', bg='violet', height=1, width=7,
               command=lambda: nhan_phim('4'))
btn_4.grid(row=3, column=0)
btn_5 = Button(may_tinh, text=' 5 ', fg='black', bg='violet', height=1, width=7,
               command=lambda: nhan_phim('5'))
btn_5.grid(row=3, column=1)
btn_6 = Button(may_tinh, text=' 6 ', fg='black', bg='violet', height=1, width=7,
               command=lambda: nhan_phim('6'))
btn_6.grid(row=3, column=2)
btn_7 = Button(may_tinh, text=' 7 ', fg='black', bg='violet', height=1, width=7,
               command=lambda: nhan_phim('7'))
btn_7.grid(row=4, column=0)
btn_8 = Button(may_tinh, text=' 8 ', fg='black', bg='violet', height=1, width=7,
               command=lambda: nhan_phim('8'))
btn_8.grid(row=4, column=1)
btn_9 = Button(may_tinh, text=' 9 ', fg='black', bg='violet', height=1, width=7,
               command=lambda: nhan_phim('9'))
btn_9.grid(row=4, column=2)
btn_0 = Button(may_tinh, text=' 0 ', fg='black', bg='violet', height=1, width=7,
               command=lambda: nhan_phim('10'))
btn_0.grid(row=5, column=0)

cong = Button(may_tinh, text=' + ', fg='black', bg='violet', height=1, width=7,
              command=lambda: nhan_phim('+'))
cong.grid(row=2, column=3)
tru = Button(may_tinh, text=' - ', fg='black', bg='violet', height=1, width=7,
             command=lambda: nhan_phim('-'))
tru.grid(row=3, column=3)
nhan = Button(may_tinh, text=' * ', fg='black', bg='violet', height=1, width=7,
              command=lambda: nhan_phim('*'))
nhan.grid(row=4, column=3)
chia = Button(may_tinh, text=' / ', fg='black', bg='violet', height=1, width=7,
              command=lambda: nhan_phim('/'))
chia.grid(row=5, column=3)
bang = Button(may_tinh, text=' = ', fg='black', bg='violet', height=1, width=7,
              command=nhan_bang)
bang.grid(row=5, column=2)
xoa = Button(may_tinh, text=' C ', fg='black', bg='violet', height=1, width=7,
             command=reset)
xoa.grid(row=5, column=1)
cham = Button(may_tinh, text='.', fg='black', bg='violet', height=1, width=7,
             command=lambda: nhan_phim('.'))
cham.grid(row=6, column=0)
may_tinh.mainloop()
