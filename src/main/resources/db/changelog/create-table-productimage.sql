DROP TABLE IF EXISTS ProductImage create table ProductImage (
   Id                   int                 identity,
   URL					nvarchar(2080)      not null,
   Sequence             int					not null,
   ProductId			int					not null,
   constraint PK_PRODUCTIMAGE primary key (Id)
)
go

alter table ProductImage
   add constraint FK_PRODUCTIMAGE_REFERENCE_PRODUCT foreign key (ProductId)
      references Product (Id)
go


