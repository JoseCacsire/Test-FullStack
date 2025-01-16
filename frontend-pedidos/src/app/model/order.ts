export class Order {
    constructor(
      public clienteId: number,
      public productos: { productoId: number; cantidad: number }[],
      public id?: number,
      public total?: number,
      public fechaCreacion?: Date,
      public estado?: string
    ) {}
  }
  