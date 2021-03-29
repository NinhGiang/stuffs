import pygame, sys, time, random
# Đặt độ khó
difficulty = 10

# Kích thước cửa sổ
width = 720
height = 480

# Khởi tạo cửa sổ
pygame.init()
pygame.display.set_caption('Snake Game')
game_window = pygame.display.set_mode((width, height))

# Khai báo màu (R, G, B)
black = pygame.Color(0, 0, 0)
white = pygame.Color(255, 255, 255)
red = pygame.Color(255, 0, 0)
green = pygame.Color(0, 255, 0)
blue = pygame.Color(0, 0, 255)

# Khai báo đồng hồ FPS (frames per second)
fps_controller = pygame.time.Clock()

# Hàm lấy vị trí ngẫu nhiên trên màn hình
def get_random_coordinate(position, size = 10):
    return random.randrange(1, (position//size)) * size

# Class con rắn
class Snake():
    def __init__(self, size = 10):
        global width, height
        self.size = size
        self.head = list()
        self.head.append(get_random_coordinate(width, size))
        self.head.append(get_random_coordinate(height, size))
        self.body = list()
        self.body.append(self.head)
    def new_head(self, direction):
        if direction == 'UP':
            self.head[1] -= self.size
        if direction == 'DOWN':
            self.head[1] += self.size
        if direction == 'LEFT':
            self.head[0] -= self.size
        if direction == 'RIGHT':
            self.head[0] += self.size
        self.body.insert(0, list(self.head))
    def move_tail(self):
        self.body.pop()
    def print_snake(self, snake_color, screen_color):
        global game_window
        game_window.fill(screen_color)
        for pos in self.body:
            pygame.draw.rect(game_window, snake_color, pygame.Rect(pos[0], pos[1], self.size, self.size))
    def check_touching_edge(self):
        if self.head[0] < 0 or self.head[0] > width - self.size:
            return True
        if self.head[1] < 0 or self.head[1] > height - self.size:
            return True
        return False
    def check_touching_itself(self):
        for pos in self.body[1:]:
            if self.head[0] == pos[0] and self.head[1] == pos[1]:
                return True
        return False

# Khai báo các biến trong trò chơi
direction = 'RIGHT'
change_to = direction
snake_size = 20
the_snake = Snake(snake_size)
score = 0
exist_food = False
food_pos = [None, None]

# Hàm tạo thức ăn
def create_food(size, food_color):
    global game_window, exist_food, food_pos, width, height
    if not exist_food:
        food_pos = [get_random_coordinate(width, size), get_random_coordinate(height, size)]
    exist_food = True
    pygame.draw.rect(game_window, food_color, pygame.Rect(food_pos[0], food_pos[1], size, size))

# Hàm in game over
def game_over():
    my_font = pygame.font.SysFont('consolas', 90)
    game_over_surface = my_font.render('YOU DIED', True, red)
    game_window.fill(black)
    game_window.blit(game_over_surface, (width/4, height/4))
    show_score(0, red, 'consolas', 20)

# Hàm in điểm
def show_score(choice, color, font, size):
    score_font = pygame.font.SysFont(font, size)
    score_surface = score_font.render('Score : ' + str(score), True, color)
    score_rect = (None, None)
    if choice == 1:
        score_rect = (width/10, 15)
    else:
        score_rect = (width/2, height/1.25)
    game_window.blit(score_surface, score_rect)

# Hàm main
def mainloop():
    global the_snake, direction, change_to, exist_food, score
    while True:
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                pygame.quit()
                sys.exit()
            # Kiểm tra nhấn phím
            elif event.type == pygame.KEYDOWN:
                # W -> Up; S -> Down; A -> Left; D -> Right
                if event.key == pygame.K_UP or event.key == ord('w'):
                    change_to = 'UP'
                if event.key == pygame.K_DOWN or event.key == ord('s'):
                    change_to = 'DOWN'
                if event.key == pygame.K_LEFT or event.key == ord('a'):
                    change_to = 'LEFT'
                if event.key == pygame.K_RIGHT or event.key == ord('d'):
                    change_to = 'RIGHT'
                # Esc -> Thoát game
                if event.key == pygame.K_ESCAPE:
                    pygame.event.post(pygame.event.Event(pygame.QUIT))
        the_snake.print_snake(green, black)
        # Chọn hướng đi (trừ hướng ngược với hướng đang đi chuyển)
        if change_to == 'UP' and direction != 'DOWN':
            direction = 'UP'
        if change_to == 'DOWN' and direction != 'UP':
            direction = 'DOWN'
        if change_to == 'LEFT' and direction != 'RIGHT':
            direction = 'LEFT'
        if change_to == 'RIGHT' and direction != 'LEFT':
            direction = 'RIGHT'
        # Thêm cái đầu
        the_snake.new_head(direction)
        # Update đồ ăn
        create_food(snake_size, red)
        # Kiểm tra có ăn được không
        if the_snake.head[0] == food_pos[0] and the_snake.head[1] == food_pos[1]:
            score += 1
            exist_food = False
        else:
            the_snake.move_tail()
        # In điểm
        show_score(1, white, 'consolas', 20)
        # Kiểm tra điều kiện thua
        if the_snake.check_touching_edge():
            game_over()
        if the_snake.check_touching_itself():
            game_over()
        # Refresh màn hình game
        pygame.display.update()
        # Đếm theo đồng hồ
        fps_controller.tick(difficulty)
# Gọi hàm main
mainloop()