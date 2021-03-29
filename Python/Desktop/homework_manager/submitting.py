print("CHƯƠNG TRÌNH LÀM BTVN")
cont = False
while not cont:
  name = input("Nhập tên của bạn: ")
  std_file = open("students.txt")
  for line in std_file.readlines():
    if name + "\n" == line:
      cont = True
  if not cont:
    print("Không tồn tại học sinh này trong danh sách làm bài")
file_name = 'work//'+ name + '.txt'
file = open(file_name, "a")
content = input("Nhập bài làm vào đây, chú ý khi làm xong thì hãy xuống dòng:\n")
file.write(content + '\n')
file.close()
file_read = open(file_name)
print(file_read.read())
