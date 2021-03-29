# import thư viện
import pygame, sys, time
from pygame.locals import *
from random import *

# khai báo màu cho 12 số
BLACK = (0, 0, 0)
RED = (244, 67, 54)
PINK = (234, 30, 99)
PURPLE = (156, 39, 176)
DEEP_PURPLE = (103, 58, 183)
BLUE = (33, 150, 243)
TEAL = (0, 150, 136)
L_GREEN = (139, 195, 74)
GREEN = (60, 175, 80)
ORANGE = (255, 152, 0)
DEEP_ORANGE = (255, 87, 34)
BROWN = (121, 85, 72)

# tạo dictionary bắt cặp một số với một màu
colour_dict = { 0:BLACK, 2:RED, 4:PINK, 8:PURPLE, 16:DEEP_PURPLE, 32:BLUE, 64:TEAL, 128:L_GREEN, 256:GREEN, 512:ORANGE, 1024: DEEP_ORANGE, 2048:BROWN}

# khai báo các hằng số trong game
TOTAL_POINTS = 0
BOARD_SIZE = 4

# khởi tạo màn hình game
pygame.init()
SURFACE = pygame.display.set_mode((400, 500), 0, 32)
pygame.display.set_caption("2048")

# hai báo font chứ cho điểm và hướng dẫn khác
myfont = pygame.font.SysFont("monospace", 25)
scorefont = pygame.font.SysFont("monospace", 50)

# khai báo ma trận chứa giá trị của các ô
tileMatrix = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]]

# viết hàm main
def main():
	# tạo 2 ô số 2 ở vị trí bất kì
	placeRandomTile()
	placeRandomTile()
	# in ma trận ra
	printMatrix()
	run = True
	while run:
		for event in pygame.event.get():
			# kiểm tra nút tắt màn hình
			if event.type == QUIT:
				run = False
				pygame.quit()
				system.exit()
			# kiểm tra liệu có khả năng đi tiếp không, nếu có:
			if checkIfCanGo() == True:
				if event.type == KEYDOWN:
					# nếu là một trong bốn phím arrow
					if isArrow(event.key):
						# xác định sẽ xoay bảng bao nhiêu lần và xoay
						rotations = getRotations(event.key)
						for i in range(0, rotations):
							rotateMatrixClockwise()
						# nếu như ô có thể di chuyển thì 
						# di chuyển (move), 
						# gộp lại (merge), 
						# tạo 1 ô mới 
						if canMove():
							moveTiles()
							mergeTiles()
							placeRandomTile()
						# xoay bảng lại như cũ
						for j in range(0, (4 - rotations) % 4):
							rotateMatrixClockwise()
						# in bảng
						printMatrix()
			# nếu không thể di chuyển:
			else:
				printGameOver()
			# nếu nhấn nút R thì reset màn hình
			if event.type == KEYDOWN:
				if event.key == pygame.K_r:
					reset()
		# cập nhật màn hình game
		pygame.display.update()

# hàm lấy màu theo giá trị số của ô
def getColour(i):
	return colour_dict[i]

# hàm in ma trận
def printMatrix():
	# tô đen màn hình
	SURFACE.fill(BLACK)
	# gọi các hằng số
	global BOARD_SIZE
	global TOTAL_POINTS
	# vẽ hình vuông theo từng hàng và từng cột
	for i in range(0, BOARD_SIZE):
		for j in range(0, BOARD_SIZE):
			# vẽ ô vuông và tô màu
			pygame.draw.rect(SURFACE, getColour(tileMatrix[i][j]), (i*(400/BOARD_SIZE),
														  j*(400/BOARD_SIZE) + 100, 400/BOARD_SIZE, 400/BOARD_SIZE))
			# điền số lên ô
			label = myfont.render(str(tileMatrix[i][j]), 1, (255,255,255))
			SURFACE.blit(label, (i*(400/BOARD_SIZE) + 30, j*(400/BOARD_SIZE) + 130))
	# in điểm lên màn hình
	label2 = scorefont.render("Score:" + str(TOTAL_POINTS), 1, (255, 255, 255))
	SURFACE.blit(label2, (10, 20))

# in màn hình khi thua
def printGameOver():
	# gọi hằng số
	global TOTAL_POINTS
	# tô đen màn hình
	SURFACE.fill(BLACK)
	# tạo và in các chữ sau lên màn hình
	label = scorefont.render("Game Over!", 1, (255,255,255))
	label2 = scorefont.render("Score:" + str(TOTAL_POINTS), 1, (255,255,255))
	label3 = myfont.render("Press r to restart!", 1, (255,255,255))
	SURFACE.blit(label, (50, 100))
	SURFACE.blit(label2, (50, 200))
	SURFACE.blit(label3, (50, 300))

# hàm đặt một ô số 2 lên vị trí bất kì
def placeRandomTile():
	# chọn một số k ngẫu nhiên từ 0 đến 15
	k = floor(random() * BOARD_SIZE * BOARD_SIZE)
	# xét xem ô tương ứng đó đã có số chưa, và thay đổi cho đến khi gặp ô số 0 
	while tileMatrix[floor(k / BOARD_SIZE)][k % BOARD_SIZE] != 0:
		k = floor(random() * BOARD_SIZE * BOARD_SIZE)
	# đặt ô đó bằng 2
	tileMatrix[floor(k / BOARD_SIZE)][k % BOARD_SIZE] = 2

# hàm làm tròn số
def floor(n):
	return int(n - (n % 1))

# hàm di chuyển số giữa các ô
def moveTiles():
	# xét di chuyển từ trái qua phải (dù nhấn phím nào cũng đã xoay bảng để chỉ xét từ phải qua trái)
	for i in range(0, BOARD_SIZE): # xét từng hàng
		for j in range(0, BOARD_SIZE - 1): # xét từng cặp số của mỗi hàng
			# cụ thể là cột (0, 1, 2, 3), (1, 2, 3) và (2, 3)
			while tileMatrix[i][j] == 0 and sum(tileMatrix[i][j:]) > 0: # nếu có ô nào số 0 và hàng đó có tổng khác 0
				for k in range(j, BOARD_SIZE - 1): # thì từ số đó trở đi qua bên phải
					tileMatrix[i][k] = tileMatrix[i][k + 1] # dồn số đó qua bên trái một bước
				tileMatrix[i][BOARD_SIZE - 1] = 0 # đặt giá trị của ô vừa mới được chuyển số thành 0
			# lặp lại hàm while tới khi số được dồn hết qua trái

# hàm ghép số lại
def mergeTiles():
	# gọi hằng số
	global TOTAL_POINTS
	# xét từng hàng
	for i in range(0, BOARD_SIZE):
		# xét từng cặp số của hàng đó
		for k in range(0, BOARD_SIZE - 1):
				# nếu 2 số liền kề bằng nhau và khác 0
				if tileMatrix[i][k] == tileMatrix[i][k + 1] and tileMatrix[i][k] != 0:
					# thì số bên phải bị dồn qua số bên trái và được đặt lại bằng không, rồi cộng điểm và di chuyển
					tileMatrix[i][k] = tileMatrix[i][k] * 2
					tileMatrix[i][k + 1] = 0
					TOTAL_POINTS += tileMatrix[i][k]
					moveTiles()

# hàm kiểm tra có bất kì 2 ô nào liền kề có cùng giá trị không
def checkIfCanGo():
	# nếu như màn hình chưa có số, bỏ qua
	for i in range(0, BOARD_SIZE ** 2):
		if tileMatrix[floor(i / BOARD_SIZE)][i % BOARD_SIZE] == 0:
			return True
	# nếu màn hình có số, xét từng số với số bên phải và bên trái của nó
	for i in range(0, BOARD_SIZE):
		for j in range(0, BOARD_SIZE - 1):
			if tileMatrix[i][j] == tileMatrix[i][j + 1]:
				return True
			elif tileMatrix[j][i] == tileMatrix[j + 1][i]:
				return True
	return False

# hàm reset game
def reset():
	global TOTAL_POINTS
	global tileMatrix
	TOTAL_POINTS = 0
	SURFACE.fill(BLACK)
	tileMatrix = [[0 for i in range(0, BOARD_SIZE)] for j in range(0, BOARD_SIZE)]
	main()

# hàm kiểm tra xem có bất kì ô trống nào để di chuyển qua trái hoặc 2 ô kề bằng nhau
def canMove():
	for i in range(0, BOARD_SIZE):
		for j in range(1, BOARD_SIZE):
			# nếu có ô trống ở bên trái một ô thì di chuyển được
			if tileMatrix[i][j-1] == 0 and tileMatrix[i][j] > 0:
				return True
			# nếu có 2 ô kề bằng nhau và khác 0 thì di chuyển được
			elif (tileMatrix[i][j-1] == tileMatrix[i][j]) and tileMatrix[i][j-1] != 0:
				return True
	return False

# hàm xoay bảng theo chiều kim đồng hồ
def rotateMatrixClockwise():
	for i in range(0, int(BOARD_SIZE/2)):
		for k in range(i, BOARD_SIZE- i - 1):
			temp1 = tileMatrix[i][k]
			temp2 = tileMatrix[BOARD_SIZE - 1 - k][i]
			temp3 = tileMatrix[BOARD_SIZE - 1 - i][BOARD_SIZE - 1 - k]
			temp4 = tileMatrix[k][BOARD_SIZE - 1 - i]

			tileMatrix[BOARD_SIZE - 1 - k][i] = temp1
			tileMatrix[BOARD_SIZE - 1 - i][BOARD_SIZE - 1 - k] = temp2
			tileMatrix[k][BOARD_SIZE - 1 - i] = temp3
			tileMatrix[i][k] = temp4

# hàm kiểm tra phím nhấn có phải là phím mũi tên không
def isArrow(k):
	return(k == pygame.K_UP or k == pygame.K_DOWN or k == pygame.K_LEFT or k == pygame.K_RIGHT)

# hàm xác định số lần xoay màn hình
def getRotations(k):
	if k == pygame.K_UP:
		return 0
	elif k == pygame.K_DOWN:
		return 2
	elif k == pygame.K_LEFT:
		return 1
	elif k == pygame.K_RIGHT:
		return 3
		
# chạy hàm main
main()