print('CHƯƠNG TRÌNH CHẤM BTVN')
cont = False
name = None
while not cont:
  name = input("Nhập tên học sinh: ")
  std_file = open("students.txt")
  for line in std_file.readlines():
    if name + "\n" == line:
      cont = True
  if not cont:
    print("Không tồn tại học sinh này trong danh sách làm bài")
file_name = 'work//'+ name + '.txt'
file = open(file_name)
print(file.read())
mark = float(input("Hãy nhập điểm của học sinh: "))
file_mark = open('marks.txt', 'a')
file_mark.write(name + ' - ' + str(mark) + '\n')
file_mark.close()