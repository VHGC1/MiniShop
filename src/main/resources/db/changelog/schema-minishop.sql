/*==============================================================*/
/* Table: Customer                                              */
/*==============================================================*/
DROP TABLE IF EXISTS Customer CREATE TABLE Customer (
   Id                   int                  identity,
   FirstName            nvarchar(100)        not null,
   LastName             nvarchar(100)        not null,
   CPF                  varchar(11)          not null unique,
   Email                varchar(255)         not null unique,
   Phone                varchar(15)          null,
   constraint PK_CUSTOMER primary key (Id)
)
go

/*==============================================================*/
/* Index: IndexCustomerName                                     */
/*==============================================================*/
create index IndexCustomerName on Customer (
   LastName ASC,
   FirstName ASC
)
go

/*==============================================================*/
/* Table: Supplier                                              */
/*==============================================================*/
DROP TABLE IF EXISTS supplier create table Supplier (
   Id                   int                  identity,
   CompanyName          nvarchar(100)        not null,
   CNPJ                 varchar(14)          not null unique,
   Email                varchar(255)         not null unique,
   City                 nvarchar(100)        not null,
   UF                   nvarchar(2)          not null,
   ContactName          nvarchar(50)         null,
   Phone                varchar(15)          null,
   constraint PK_SUPPLIER primary key (Id)
)
go

/*==============================================================*/
/* Index: IndexSupplierName                                     */
/*==============================================================*/
create index IndexSupplierName on Supplier (
   CompanyName ASC
)
go

/*==============================================================*/
/* Table: Product                                               */
/*==============================================================*/
DROP TABLE IF EXISTS product create table Product (
   Id                   int                  identity,
   ProductName          nvarchar(100)        not null,
   SupplierId           int                  not null,
   UnitPrice            decimal(12,2)        not null,
   IsDiscontinued       bit                  not null default 0,
   PackageName          nvarchar(100)        null,
   constraint PK_PRODUCT primary key (Id)
)
go

/*==============================================================*/
/* Index: IndexProductSupplierId                                */
/*==============================================================*/
create index IndexProductSupplierId on Product (
   SupplierId ASC
)
go

/*==============================================================*/
/* Index: IndexProductName                                      */
/*==============================================================*/
create index IndexProductName on Product (
   ProductName ASC
)
go

/*==============================================================*/
/* Table: "CustomerOrder"                                               */
/*==============================================================*/
DROP TABLE IF EXISTS CustomerOrder create table CustomerOrder (
   Id                   int                  identity,
   OrderDate            datetime             not null default getdate(),
   CustomerId           int                  not null,
   TotalAmount          decimal(12,2)        not null,
   constraint PK_ORDER primary key (Id)
)
go

/*==============================================================*/
/* Index: IndexOrderCustomerId                                  */
/*==============================================================*/
create index IndexOrderCustomerId on CustomerOrder (
   CustomerId ASC
)
go

/*==============================================================*/
/* Index: IndexOrderOrderDate                                   */
/*==============================================================*/
create index IndexOrderOrderDate on CustomerOrder (
   OrderDate ASC
)
go

/*==============================================================*/
/* Table: OrderItem                                             */
/*==============================================================*/
DROP TABLE IF EXISTS OrderItem create table OrderItem (
   Id                   int                  identity,
   OrderId              int                  not null,
   ProductId            int                  not null,
   UnitPrice            decimal(12,2)        not null,
   Quantity             int                  not null,
   constraint PK_ORDERITEM primary key (Id)
)
go

/*==============================================================*/
/* Index: IndexOrderItemOrderId                                 */
/*==============================================================*/
create index IndexOrderItemOrderId on OrderItem (
   OrderId ASC
)
go

/*==============================================================*/
/* Index: IndexOrderItemProductId                               */
/*==============================================================*/
create index IndexOrderItemProductId on OrderItem (
   ProductId ASC
)
go

/*==============================================================*/
/* Constraints                                                  */
/*==============================================================*/
alter table CustomerOrder
   add constraint FK_ORDER_REFERENCE_CUSTOMER foreign key (CustomerId)
      references Customer (Id)
go

alter table OrderItem
   add constraint FK_ORDERITE_REFERENCE_ORDER foreign key (OrderId)
      references CustomerOrder (Id)
go

alter table OrderItem
   add constraint FK_ORDERITE_REFERENCE_PRODUCT foreign key (ProductId)
      references Product (Id)
go

alter table Product
   add constraint FK_PRODUCT_REFERENCE_SUPPLIER foreign key (SupplierId)
      references Supplier (Id)
go