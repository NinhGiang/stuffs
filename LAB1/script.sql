USE [master]
GO
/****** Object:  Database [TheQuiz]    Script Date: 6/3/2020 10:39:40 PM ******/
CREATE DATABASE [TheQuiz]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TheQuiz', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\TheQuiz.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'TheQuiz_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\TheQuiz_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [TheQuiz] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TheQuiz].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [TheQuiz] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [TheQuiz] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [TheQuiz] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [TheQuiz] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [TheQuiz] SET ARITHABORT OFF 
GO
ALTER DATABASE [TheQuiz] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [TheQuiz] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [TheQuiz] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [TheQuiz] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [TheQuiz] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [TheQuiz] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [TheQuiz] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [TheQuiz] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [TheQuiz] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [TheQuiz] SET  DISABLE_BROKER 
GO
ALTER DATABASE [TheQuiz] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [TheQuiz] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [TheQuiz] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [TheQuiz] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [TheQuiz] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [TheQuiz] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [TheQuiz] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [TheQuiz] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [TheQuiz] SET  MULTI_USER 
GO
ALTER DATABASE [TheQuiz] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [TheQuiz] SET DB_CHAINING OFF 
GO
ALTER DATABASE [TheQuiz] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [TheQuiz] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [TheQuiz] SET DELAYED_DURABILITY = DISABLED 
GO
USE [TheQuiz]
GO
/****** Object:  Table [dbo].[tblQuestion]    Script Date: 6/3/2020 10:39:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblQuestion](
	[questionContent] [nvarchar](250) NOT NULL,
	[optionA] [nvarchar](250) NULL,
	[optionB] [nvarchar](250) NULL,
	[optionC] [nvarchar](250) NULL,
	[optionD] [nvarchar](250) NULL,
	[answer] [nvarchar](250) NOT NULL,
	[subjectCode] [nvarchar](50) NOT NULL,
	[isActive] [bit] NOT NULL,
	[questionCode] [int] IDENTITY(1,1) NOT NULL,
 CONSTRAINT [PK_tblQuestion] PRIMARY KEY CLUSTERED 
(
	[questionCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblQuiz]    Script Date: 6/3/2020 10:39:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblQuiz](
	[quizCode] [int] IDENTITY(1,1) NOT NULL,
	[quantity] [int] NOT NULL,
	[correctAnswers] [int] NOT NULL,
	[point] [float] NOT NULL,
	[subjectCode] [nvarchar](50) NOT NULL,
	[takenTime] [datetime] NOT NULL,
	[email] [nvarchar](250) NOT NULL,
 CONSTRAINT [PK_tblQuiz] PRIMARY KEY CLUSTERED 
(
	[quizCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblRole]    Script Date: 6/3/2020 10:39:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblRole](
	[isRole] [int] IDENTITY(1,1) NOT NULL,
	[roleName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblRole] PRIMARY KEY CLUSTERED 
(
	[isRole] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblStatus]    Script Date: 6/3/2020 10:39:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblStatus](
	[isStatus] [int] IDENTITY(1,1) NOT NULL,
	[statusName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblStatus] PRIMARY KEY CLUSTERED 
(
	[isStatus] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblSubject]    Script Date: 6/3/2020 10:39:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblSubject](
	[subjectCode] [nvarchar](50) NOT NULL,
	[name] [nvarchar](250) NOT NULL,
	[quantity] [int] NULL,
	[timer] [int] NULL,
 CONSTRAINT [PK_tblSubject] PRIMARY KEY CLUSTERED 
(
	[subjectCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 6/3/2020 10:39:40 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[email] [nvarchar](250) NOT NULL,
	[password] [varchar](80) NOT NULL,
	[fullName] [nvarchar](250) NOT NULL,
	[isRole] [int] NOT NULL,
	[isStatus] [int] NOT NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[tblQuestion] ON 

INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'What is a prototype?', N'A model', N'A sample', N'A protocol', N'An example', N'A sample', N'PRF192', 1, 1)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'How many memory segments are there during a program execution?', N'One', N'Two', N'Three', N'Four', N'One', N'PRF192', 1, 2)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'What is the result of 1 + 1?', N'0', N'1', N'2', N'3', N'2', N'CSI101', 1, 3)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'Name the author of Harry Potter', N'J.K.Rowling', N'Alexander ', N'Hermione ', N'Ron Weasley ', N'J.K.Rowling ', N'CSI101', 1, 4)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'What is the result of 1/0', N'Undefined', N'Infinite', N'1', N'0', N'Undefined', N'PRF192', 1, 5)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'The sequeue of The Originals is ', N'Vampire Diary', N'The Originals sesson 2', N'The Legacies ', N'The Legacies sesson 2', N'The Legacies ', N'PRF192', 1, 6)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'The series "Gossip Girls" has how many seasons', N'2', N'3', N'6', N'7', N'7', N'PRF192', 1, 7)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'In the movie series Pirates of the Caribbeans, in which movie we have the Flying Dutchman', N'The movie 1', N'The movie 2', N'The movie 3', N'The movie 4', N'The movie 3', N'PRF192', 0, 8)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'In series ', N'Blair Wardof ', N'Serena Van de Woosen ', N'Chuck Bass', N'Dan Humphrey ', N'Dan Humphrey ', N'PRF192', 0, 9)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'In series ', N'Lucifer Morningstar', N'Father Blackwood', N'Adward Spellman ', N'Harvey ', N'Lucifer Morningstar', N'CSI101', 1, 10)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'In Riverdale sesson 1, who is Red Dahlia true identity?', N'Cherryl Blossom', N'Chuck Head', N'Jason Blosoom', N'Penelope Blossom', N'Penelope Blossom', N'PRF192', 0, 11)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'In Riverdale, Black Hood true identity is', N'Hall Cooper', N'Betty Cooper', N'Polly Cooper', N'Alice Cooper', N'Hall Cooper', N'CSI101', 0, 12)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'In ', N'His enemies', N'His best friend', N'His brother', N'His lover', N'His best friend', N'PRF192', 1, 13)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'The oldest sibling among the Mikaelsons is', N'Frey', N'Finn', N'Elijah', N'Nick Klause', N'Frey', N'PRF192', 1, 14)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'According to Klaus in The Originals, who does he consider as his son', N'Kol', N'Marcel', N'Lucien', N'Damon', N'Lucien', N'PRF192', 1, 15)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'In The Originals, the newest member of Mikaelsons family is', N'Hope Mikaelson', N'Rebekah Mikaelson', N'Freya Mikaelson', N'Cami ', N'Rebekah Mikaelson', N'CSI101', 1, 16)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'In The Dynasty, who did Fallon Carrington almost married to?', N'Liam Ridley', N'Jeff Colby', N'Monica Colby', N'Robbey Reid', N'Jeff Colby', N'CSI101', 1, 17)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'In The Dynasty, how did Jeff Colby And Monica Colby can earn the heir-share of Carrington Atlantis after Tom - Fallon grandpa dead?', N'Fallon''s father had an affair with their mom', N'Fallon''s mother had an affair with their dad', N'They fake the paper work and steal the share', N'Their grandma had an affair with Fallon''s grandpa', N'Their grandma had an affair with Fallon''s grandpa', N'CSI101', 1, 18)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'In The Dynasty, Steven Carrington is actually:', N'Ander''s child', N'Blake Carrington''s child', N'Jeff Colby''s child', N'Adam Carrington''s child', N'Ander''s child', N'CSI101', 1, 19)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'In The Chillign Adventure of Sabrina, Sabrina is described in the prophecy as:  ', N'The child of Night who shall be queen', N'The girl who destroy all word ', N'The rising star will swallow the sun ', N'The heir of Lucifer Morningstar on Earth', N'The child of Night who shall be queen', N'CSI101', 0, 20)
INSERT [dbo].[tblQuestion] ([questionContent], [optionA], [optionB], [optionC], [optionD], [answer], [subjectCode], [isActive], [questionCode]) VALUES (N'Which song below is one of the Rock Anthem of the 90s?', N'The Man Who Sold The World', N'Yellow', N'Smells Like Teen Spirit', N'Learn To Fly', N'Smells Like Teen Spirit', N'PRF192', 1, 1004)
SET IDENTITY_INSERT [dbo].[tblQuestion] OFF
SET IDENTITY_INSERT [dbo].[tblQuiz] ON 

INSERT [dbo].[tblQuiz] ([quizCode], [quantity], [correctAnswers], [point], [subjectCode], [takenTime], [email]) VALUES (2, 4, 2, 5, N'CSI101', CAST(N'2020-06-02T21:44:17.813' AS DateTime), N'gianghtn@domain.com')
INSERT [dbo].[tblQuiz] ([quizCode], [quantity], [correctAnswers], [point], [subjectCode], [takenTime], [email]) VALUES (3, 4, 0, 0, N'CSI101', CAST(N'2020-06-03T07:15:01.517' AS DateTime), N'gianghtn@domain.com')
INSERT [dbo].[tblQuiz] ([quizCode], [quantity], [correctAnswers], [point], [subjectCode], [takenTime], [email]) VALUES (4, 5, 1, 2, N'PRF192', CAST(N'2020-06-03T11:34:03.780' AS DateTime), N'gianghtn@domain.com')
INSERT [dbo].[tblQuiz] ([quizCode], [quantity], [correctAnswers], [point], [subjectCode], [takenTime], [email]) VALUES (5, 5, 0, 0, N'PRF192', CAST(N'2020-06-03T11:42:16.907' AS DateTime), N'gianghtn@domain.com')
INSERT [dbo].[tblQuiz] ([quizCode], [quantity], [correctAnswers], [point], [subjectCode], [takenTime], [email]) VALUES (6, 4, 0, 0, N'CSI101', CAST(N'2020-06-03T13:53:11.867' AS DateTime), N'gianghtn@domain.com')
INSERT [dbo].[tblQuiz] ([quizCode], [quantity], [correctAnswers], [point], [subjectCode], [takenTime], [email]) VALUES (7, 5, 3, 6, N'PRF192', CAST(N'2020-06-03T21:18:21.987' AS DateTime), N'gianghtn@domain.com')
INSERT [dbo].[tblQuiz] ([quizCode], [quantity], [correctAnswers], [point], [subjectCode], [takenTime], [email]) VALUES (8, 5, 1, 2, N'PRF192', CAST(N'2020-06-03T22:19:14.740' AS DateTime), N'gianghtn@domain.com')
SET IDENTITY_INSERT [dbo].[tblQuiz] OFF
SET IDENTITY_INSERT [dbo].[tblRole] ON 

INSERT [dbo].[tblRole] ([isRole], [roleName]) VALUES (1, N'Admin')
INSERT [dbo].[tblRole] ([isRole], [roleName]) VALUES (2, N'Student')
SET IDENTITY_INSERT [dbo].[tblRole] OFF
SET IDENTITY_INSERT [dbo].[tblStatus] ON 

INSERT [dbo].[tblStatus] ([isStatus], [statusName]) VALUES (1, N'New')
SET IDENTITY_INSERT [dbo].[tblStatus] OFF
INSERT [dbo].[tblSubject] ([subjectCode], [name], [quantity], [timer]) VALUES (N'CSI101', N'Introduction to Computer Science', 4, 300)
INSERT [dbo].[tblSubject] ([subjectCode], [name], [quantity], [timer]) VALUES (N'PRF192', N'Programming Fundamentals with C', 5, 420)
INSERT [dbo].[tblUser] ([email], [password], [fullName], [isRole], [isStatus]) VALUES (N'giang@domain.com', N'giang', N'ha thi ninh giang', 2, 1)
INSERT [dbo].[tblUser] ([email], [password], [fullName], [isRole], [isStatus]) VALUES (N'gianghtn@domain.com', N'edfa9f5a9a9ea7f86e8863676788f90987df514aa08d3745c050f1d8f78861c2', N'ha thi ninh giang', 2, 1)
INSERT [dbo].[tblUser] ([email], [password], [fullName], [isRole], [isStatus]) VALUES (N'tamntt@domain.com', N'402df5462bdf31121df249ac203868c2c9337d25f234e31735aa5eca9a2ec62d', N'nguyen the thanh tam', 2, 1)
INSERT [dbo].[tblUser] ([email], [password], [fullName], [isRole], [isStatus]) VALUES (N'uyennlp@domain.com', N'69889ed7445137b9987aa0be21ac124346cd87ef1939cd091a764320a6293115', N'nguyen le phuong uyen', 1, 1)
ALTER TABLE [dbo].[tblQuestion]  WITH CHECK ADD  CONSTRAINT [FK_tblQuestion_tblSubject] FOREIGN KEY([subjectCode])
REFERENCES [dbo].[tblSubject] ([subjectCode])
GO
ALTER TABLE [dbo].[tblQuestion] CHECK CONSTRAINT [FK_tblQuestion_tblSubject]
GO
ALTER TABLE [dbo].[tblQuiz]  WITH CHECK ADD  CONSTRAINT [FK_tblQuiz_tblSubject] FOREIGN KEY([subjectCode])
REFERENCES [dbo].[tblSubject] ([subjectCode])
GO
ALTER TABLE [dbo].[tblQuiz] CHECK CONSTRAINT [FK_tblQuiz_tblSubject]
GO
ALTER TABLE [dbo].[tblQuiz]  WITH CHECK ADD  CONSTRAINT [FK_tblQuiz_tblUser] FOREIGN KEY([email])
REFERENCES [dbo].[tblUser] ([email])
GO
ALTER TABLE [dbo].[tblQuiz] CHECK CONSTRAINT [FK_tblQuiz_tblUser]
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD  CONSTRAINT [FK_tblUser_tblRole] FOREIGN KEY([isRole])
REFERENCES [dbo].[tblRole] ([isRole])
GO
ALTER TABLE [dbo].[tblUser] CHECK CONSTRAINT [FK_tblUser_tblRole]
GO
ALTER TABLE [dbo].[tblUser]  WITH CHECK ADD  CONSTRAINT [FK_tblUser_tblStatus] FOREIGN KEY([isStatus])
REFERENCES [dbo].[tblStatus] ([isStatus])
GO
ALTER TABLE [dbo].[tblUser] CHECK CONSTRAINT [FK_tblUser_tblStatus]
GO
USE [master]
GO
ALTER DATABASE [TheQuiz] SET  READ_WRITE 
GO
