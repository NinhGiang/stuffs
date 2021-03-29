from tkinter import *
window = Tk()
window.title('Currency Converter')
options_set = {'VND', 'USD', 'KRW', 'EUR', 'JPY'}
var_from = DoubleVar()
var_to = DoubleVar()
choose_fr = StringVar()
choose_fr.set('VND')
choose_to = StringVar()
choose_to.set('USD')
def convert(fr, to, amount):
    to_vnd = 1
    from_vnd = 1
    currency_list = ['VND', 'USD', 'KRW', 'EUR', 'JPY']
    to_vnd_rate = [1, 23129, 21.18, 28012.92, 222.34]
    from_vnd_rate = [1, 0.000043, 0.047, 0.000036, 0.0045]
    for c in currency_list:
        if fr.get() == c:
            to_vnd = to_vnd_rate[currency_list.index(c)]
        if to.get() == c:
            from_vnd = from_vnd_rate[currency_list.index(c)]
    result = amount.get()*to_vnd*from_vnd
    var_to.set(result)
def create():
    lbl_from = Label(window, text = 'From: ')
    lbl_to = Label(window, text = 'To: ')
    txt_from = Entry(window, width=30, textvariable = var_from)
    txt_to = Entry(window, width=30, textvariable = var_to)
    op_from = OptionMenu(window, choose_fr, *options_set)
    op_to = OptionMenu(window, choose_to, *options_set)
    btn_convert = Button(window, text='Convert', command = lambda: convert(choose_fr, choose_to, var_from))
    lbl_from.grid(row = 0, column = 0)
    lbl_to.grid(row = 1, column = 0)
    txt_from.grid(row = 0, column = 1)
    txt_to.grid(row = 1, column = 1)
    op_from.grid(row = 0, column = 2)
    op_to.grid(row = 1, column = 2)
    btn_convert.grid(row = 2, column = 3)
    window.mainloop()
create()