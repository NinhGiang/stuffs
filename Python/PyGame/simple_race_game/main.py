# import necessary modules
import pygame
import random
import time

pygame.init()

# set up properties
display_width = 800
display_height = 600
black = (0,0,0)
white = (255,255,255)
red = (255,0,0)
green = (124,252,0)
grey = (105,105,105)

# set up screen
screen = pygame.display.set_mode((display_width,display_height))
screen.fill(white)
pygame.display.set_caption('Race Game by Ninh Giang')
clock = pygame.time.Clock()

# set up object properties
car_img = "E:\\TEKY\\Python\\PyGame_Level_4\\simple_race_game\\racecar.png"
obstacle_y = -100
car_size = 73
x_change = 0
obstacle_color_list = [black, red]
score = 0

# class Car
class Car:
    def __init__(self, img_source, x = 100, y = 100):
        self.img = pygame.image.load(img_source)
        self.x = x
        self.y = y
        screen.blit(self.img,(self.x,self.y))
    def move(self, x_change):
        self.x += x_change
        screen.blit(self.img,(self.x,self.y))

# class Obstacle
class Obstacle:
    def __init__(self, color, y, width = 100, height = 100):
        self.color = color
        self.x = random.randrange(100, display_width - 100 - height)
        self.y = y
        self.w = width
        self.h = height
        pygame.draw.rect(screen, color, [self.x, self.y, self.w, self.h])
    def move(self, speed = 7):
        self.y += speed
        pygame.draw.rect(screen, self.color, [self.x, self.y, self.w, self.h])
    def reset(self):
        self.x = random.randrange(100, display_width - 100 - self.h)
        self.y = 0 - self.h
        self.color = random.choice(obstacle_color_list)

# screen_text
def text_objects(msg, font):
    textSurface = font.render(msg, True, black)
    return textSurface, textSurface.get_rect()
def message_center(msg):
    largeText = pygame.font.Font('freesansbold.ttf',115)
    TextSurf, TextRect = text_objects(msg, largeText)
    TextRect.center = ((display_width/2),(display_height/2))
    screen.blit(TextSurf, TextRect)
    pygame.display.update()
    time.sleep(2)
def message_score(score):
    smallText = pygame.font.Font('freesansbold.ttf',20)
    TextSurf, TextRect = text_objects("Score: " + str(score), smallText)
    TextRect.center = (50, 20)
    screen.blit(TextSurf, TextRect)

# some events
def crash():
    message_center('You Lose!')

def game_loop():
    global x_change, score
    obstacle = Obstacle(black, obstacle_y)
    car = Car(car_img, 100, 500)
    game_continue = True
    while game_continue:

        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                game_continue = False
                break
        # control car by keys
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_LEFT:
                x_change = -5
            if event.key == pygame.K_RIGHT:
                x_change = 5
        if event.type == pygame.KEYUP:
            if event.key == pygame.K_LEFT or event.key == pygame.K_RIGHT:
                x_change = 0

        # Update screen every 60 milisecs
        # Redraw screen
        screen.fill(green)
        # Draw The Road
        pygame.draw.rect(screen, grey, [100, 0, 600, 600])
        # Draw Line painting on the road
        pygame.draw.line(screen, white, [400, 0], [400, 600], 20)
        # Update score
        message_score(score)

        # Update change of car
        car.move(x_change)
        # Update change of obstacle
        obstacle.move(10)

        # End if car move out of bound
        if car.x > display_width - car_size or car.x < 0:
            crash()
            game_continue = False

        # Reset obstacle.y when obstacle move over the bottom size
        if obstacle.y > display_height:
            score += 1
            obstacle.reset()

        # Check for the crash
        # Condition 1: car reachs obstacle honrizontally
        if car.y < obstacle.y + obstacle.h:
            print('y crossover')
            # Condition 2: Crash from the right or crash from the left:
            if car.x > obstacle.x and car.x < obstacle.x + obstacle.w or car.x + car_size > obstacle.x and car.x + car_size < obstacle.x + obstacle.w:
                print('x crossover')
                crash()
                game_continue = False

        # End a loop
        pygame.display.update()
        clock.tick(60)

game_loop()
pygame.quit()
