print('1. Submitting (for students)')
print('2. Marking (for teachers)')
try:
  choice = int(input('Enter your choice (1 or 2): '))
  if choice < 1 or choice > 2:
    raise Exception('invalid option value')
except:
  print('Error orcured')
if choice == 1:
  import submitting
  submitting
else:
  import marking
  marking