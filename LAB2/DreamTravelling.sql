USE [master]
GO
/****** Object:  Database [DreamTravelling]    Script Date: 6/21/2020 11:56:36 PM ******/
CREATE DATABASE [DreamTravelling]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'DreamTravelling', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\DreamTravelling.mdf' , SIZE = 3072KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'DreamTravelling_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.SQLEXPRESS\MSSQL\DATA\DreamTravelling_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [DreamTravelling] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [DreamTravelling].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [DreamTravelling] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [DreamTravelling] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [DreamTravelling] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [DreamTravelling] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [DreamTravelling] SET ARITHABORT OFF 
GO
ALTER DATABASE [DreamTravelling] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [DreamTravelling] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [DreamTravelling] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [DreamTravelling] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [DreamTravelling] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [DreamTravelling] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [DreamTravelling] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [DreamTravelling] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [DreamTravelling] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [DreamTravelling] SET  DISABLE_BROKER 
GO
ALTER DATABASE [DreamTravelling] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [DreamTravelling] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [DreamTravelling] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [DreamTravelling] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [DreamTravelling] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [DreamTravelling] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [DreamTravelling] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [DreamTravelling] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [DreamTravelling] SET  MULTI_USER 
GO
ALTER DATABASE [DreamTravelling] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [DreamTravelling] SET DB_CHAINING OFF 
GO
ALTER DATABASE [DreamTravelling] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [DreamTravelling] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [DreamTravelling] SET DELAYED_DURABILITY = DISABLED 
GO
USE [DreamTravelling]
GO
/****** Object:  Table [dbo].[tblDiscount]    Script Date: 6/21/2020 11:56:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblDiscount](
	[discountCode] [nvarchar](50) NOT NULL,
	[expiryDate] [date] NOT NULL,
	[discountType] [int] NOT NULL,
	[amount] [int] NOT NULL,
 CONSTRAINT [PK_tblDiscount] PRIMARY KEY CLUSTERED 
(
	[discountCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblInvoice]    Script Date: 6/21/2020 11:56:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblInvoice](
	[invoiceID] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[tourID] [int] NOT NULL,
	[quantity] [int] NOT NULL,
	[discountCode] [nvarchar](50) NULL,
	[total] [float] NOT NULL,
	[datePurchase] [date] NOT NULL,
 CONSTRAINT [PK_tblInvoice] PRIMARY KEY CLUSTERED 
(
	[invoiceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblPlace]    Script Date: 6/21/2020 11:56:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblPlace](
	[placeID] [int] IDENTITY(1,1) NOT NULL,
	[placeName] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_tblPlace] PRIMARY KEY CLUSTERED 
(
	[placeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblTour]    Script Date: 6/21/2020 11:56:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblTour](
	[tourID] [int] IDENTITY(1,1) NOT NULL,
	[tourName] [nvarchar](250) NOT NULL,
	[fromPlace] [nvarchar](250) NOT NULL,
	[toPlace] [nvarchar](250) NOT NULL,
	[fromDate] [date] NOT NULL,
	[toDate] [date] NOT NULL,
	[price] [float] NOT NULL,
	[quota] [int] NOT NULL,
	[image] [nvarchar](250) NULL,
	[dateImport] [date] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblTour] PRIMARY KEY CLUSTERED 
(
	[tourID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tblUser]    Script Date: 6/21/2020 11:56:37 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tblUser](
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](250) NOT NULL,
	[fullname] [nvarchar](250) NOT NULL,
	[role] [bit] NOT NULL,
	[status] [bit] NOT NULL,
 CONSTRAINT [PK_tblUser] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tblDiscount] ([discountCode], [expiryDate], [discountType], [amount]) VALUES (N'NEWUSER50USD', CAST(N'2020-12-31' AS Date), 1, 50)
INSERT [dbo].[tblDiscount] ([discountCode], [expiryDate], [discountType], [amount]) VALUES (N'SALEHE50', CAST(N'2020-06-01' AS Date), 2, 50)
INSERT [dbo].[tblDiscount] ([discountCode], [expiryDate], [discountType], [amount]) VALUES (N'SALEHOINAMNGOAI', CAST(N'2019-12-31' AS Date), 1, 100)
INSERT [dbo].[tblDiscount] ([discountCode], [expiryDate], [discountType], [amount]) VALUES (N'SALETET30', CAST(N'2021-01-25' AS Date), 2, 30)
SET IDENTITY_INSERT [dbo].[tblInvoice] ON 

INSERT [dbo].[tblInvoice] ([invoiceID], [username], [tourID], [quantity], [discountCode], [total], [datePurchase]) VALUES (2, N'giang', 6, 1, N'SALEHE50', 800, CAST(N'2020-06-21' AS Date))
INSERT [dbo].[tblInvoice] ([invoiceID], [username], [tourID], [quantity], [discountCode], [total], [datePurchase]) VALUES (3, N'giang', 3, 1, N'SALEHE50', 800, CAST(N'2020-06-21' AS Date))
INSERT [dbo].[tblInvoice] ([invoiceID], [username], [tourID], [quantity], [discountCode], [total], [datePurchase]) VALUES (4, N'giang', 5, 2, N'SALEHE50', 800, CAST(N'2020-06-21' AS Date))
INSERT [dbo].[tblInvoice] ([invoiceID], [username], [tourID], [quantity], [discountCode], [total], [datePurchase]) VALUES (5, N'giang', 1, 1, NULL, 200, CAST(N'2020-06-21' AS Date))
INSERT [dbo].[tblInvoice] ([invoiceID], [username], [tourID], [quantity], [discountCode], [total], [datePurchase]) VALUES (8, N'giang', 1, 1, NULL, 500, CAST(N'2020-06-21' AS Date))
INSERT [dbo].[tblInvoice] ([invoiceID], [username], [tourID], [quantity], [discountCode], [total], [datePurchase]) VALUES (9, N'giang', 2, 1, NULL, 500, CAST(N'2020-06-21' AS Date))
SET IDENTITY_INSERT [dbo].[tblInvoice] OFF
SET IDENTITY_INSERT [dbo].[tblTour] ON 

INSERT [dbo].[tblTour] ([tourID], [tourName], [fromPlace], [toPlace], [fromDate], [toDate], [price], [quota], [image], [dateImport], [status]) VALUES (1, N'Đà Lạt - thành phố mộng mơ', N'Ho Chi Minh', N'Da Lat', CAST(N'2020-08-15' AS Date), CAST(N'2020-08-18' AS Date), 200, 30, N'https://travel.com.vn/images/destination/Large/tf_191011045900_143553.jpg', CAST(N'2020-06-18' AS Date), 1)
INSERT [dbo].[tblTour] ([tourID], [tourName], [fromPlace], [toPlace], [fromDate], [toDate], [price], [quota], [image], [dateImport], [status]) VALUES (2, N'Trở về phố cổ Hội An', N'Ho Chi Minh', N'Hoi An', CAST(N'2020-04-18' AS Date), CAST(N'2020-04-22' AS Date), 300, 30, N'https://travel.com.vn/images/destination/Large/tf_191011045900_143553.jpg', CAST(N'2020-06-18' AS Date), 1)
INSERT [dbo].[tblTour] ([tourID], [tourName], [fromPlace], [toPlace], [fromDate], [toDate], [price], [quota], [image], [dateImport], [status]) VALUES (3, N'Trải nghiệm ở nhà nổi vùng sông nước', N'Da Nang', N'Ca Mau', CAST(N'2020-05-10' AS Date), CAST(N'0202-05-15' AS Date), 300, 30, N'https://travel.com.vn/images/destination/Large/tf_191011045900_143553.jpg', CAST(N'2020-06-17' AS Date), 1)
INSERT [dbo].[tblTour] ([tourID], [tourName], [fromPlace], [toPlace], [fromDate], [toDate], [price], [quota], [image], [dateImport], [status]) VALUES (4, N'Khám phá lịch sử Côn Đảo', N'Ha Noi', N'Phu Quoc', CAST(N'2020-09-20' AS Date), CAST(N'2020-09-24' AS Date), 500, 30, N'https://travel.com.vn/images/destination/Large/tf_191011045900_143553.jpg', CAST(N'2020-06-19' AS Date), 1)
INSERT [dbo].[tblTour] ([tourID], [tourName], [fromPlace], [toPlace], [fromDate], [toDate], [price], [quota], [image], [dateImport], [status]) VALUES (5, N'Trải nghiệm homestay tại Sapa', N'Da Nang', N'Ba Vi', CAST(N'2020-05-14' AS Date), CAST(N'2020-05-20' AS Date), 550, 30, N'https://travel.com.vn/images/destination/Large/tf_191011045900_143553.jpg', CAST(N'2020-06-10' AS Date), 1)
INSERT [dbo].[tblTour] ([tourID], [tourName], [fromPlace], [toPlace], [fromDate], [toDate], [price], [quota], [image], [dateImport], [status]) VALUES (6, N'Cắm trại tại Vũng Tàu - thành phố biển hiện đại', N'Ha Noi', N'Ba Ria Vung Tau', CAST(N'2020-05-10' AS Date), CAST(N'2020-05-14' AS Date), 200, 30, N'https://travel.com.vn/images/destination/Large/tf_191011045900_143553.jpg', CAST(N'2020-05-01' AS Date), 1)
INSERT [dbo].[tblTour] ([tourID], [tourName], [fromPlace], [toPlace], [fromDate], [toDate], [price], [quota], [image], [dateImport], [status]) VALUES (7, N'Vinh Ha Long - Di san thien nhien the gioi', N'Ho Chi Minh', N'Quang Ninh', CAST(N'2020-06-21' AS Date), CAST(N'2020-06-24' AS Date), 700, 30, N'https://th.bing.com/th/id/OIP.oglz0yxxjGNMqEJwtiYHLAHaEK?pid=Api&rs=1', CAST(N'2020-06-21' AS Date), 1)
SET IDENTITY_INSERT [dbo].[tblTour] OFF
INSERT [dbo].[tblUser] ([username], [password], [fullname], [role], [status]) VALUES (N'giang', N'giang', N'ha thi ninh giang', 1, 0)
ALTER TABLE [dbo].[tblInvoice]  WITH CHECK ADD  CONSTRAINT [FK_tblInvoice_tblDiscount] FOREIGN KEY([discountCode])
REFERENCES [dbo].[tblDiscount] ([discountCode])
GO
ALTER TABLE [dbo].[tblInvoice] CHECK CONSTRAINT [FK_tblInvoice_tblDiscount]
GO
ALTER TABLE [dbo].[tblInvoice]  WITH CHECK ADD  CONSTRAINT [FK_tblInvoice_tblTour] FOREIGN KEY([tourID])
REFERENCES [dbo].[tblTour] ([tourID])
GO
ALTER TABLE [dbo].[tblInvoice] CHECK CONSTRAINT [FK_tblInvoice_tblTour]
GO
ALTER TABLE [dbo].[tblInvoice]  WITH CHECK ADD  CONSTRAINT [FK_tblInvoice_tblUser] FOREIGN KEY([username])
REFERENCES [dbo].[tblUser] ([username])
GO
ALTER TABLE [dbo].[tblInvoice] CHECK CONSTRAINT [FK_tblInvoice_tblUser]
GO
USE [master]
GO
ALTER DATABASE [DreamTravelling] SET  READ_WRITE 
GO
