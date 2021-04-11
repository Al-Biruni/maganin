export interface ICategoryMaganin {
  id?: number;
  categoryTypeId?: string;
  categoryName?: string;
  parentId?: number;
  description?: string;
  imageURL?: string;
  hideSearch?: boolean;
}

export class CategoryMaganin implements ICategoryMaganin {
  constructor(
    public id?: number,
    public categoryTypeId?: string,
    public categoryName?: string,
    public parentId?: number,
    public description?: string,
    public imageURL?: string,
    public hideSearch?: boolean
  ) {
    this.hideSearch = this.hideSearch || false;
  }
}
