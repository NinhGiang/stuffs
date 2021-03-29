from tkinter import *
import obj as ob
import openpyxl
import xlsxwriter

lst = []
def create():
    windown = Tk()
    windown.title('check invoice')
    lbl_title = Label(windown, text='invoice of all branches', font=('Times New Roman', 15))
    lbl.grid(row = 0, column = 0, columnspan = 2)

    #edit file excel
    workbook = xlsxwriter.Workbook('invoice_of_all_branches')
    worrksheet = workbook.get_worksheet_by_name('Sheet1')
    worksheet.write(0,6,'')
    worksheet.write(2,6,'')

    #open file excel
    wb = openpyxl.load_workbook(filename='invoice_of_all_branches.xlsx')
    ws = wb.active
    for row in ws.values:
        hang = []
        for value in row:
            hang.append(value)
        lst.append(hang)
    table = ob.Table(windown)
    table.set_data(lst)
    table.print(2,0)
