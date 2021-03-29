import pygame
pygame.init()
screen = pygame.display.set_mode((700, 500))
# khai báo màu
red = (255, 0, 0)
white = (255,255,255)
black = (0, 0, 0)
screen.fill(white)

# vẽ sử dung pygame.draw.() - lines, rect, circle, ellipse, polygon
pygame.draw.rect(screen, red, (100, 100, 480, 300), 0, border_radius=40)
pygame.draw.polygon(screen, white, ((280, 180), (280, 320), (420, 250)))
pygame.draw.lines(screen, black, False, ((764, 372), (161, 762), (284, 464)), 5)
pygame.draw.circle(screen, black, (320, 250), 50, 0)

score = "Hello"
score_font = pygame.font.SysFont("comicsansms", 35)
value = score_font.render(score, True, black)
screen.blit(value, [0, 0])
pygame.display.update()

running = True
while running:
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            running = False
pygame.quit()
