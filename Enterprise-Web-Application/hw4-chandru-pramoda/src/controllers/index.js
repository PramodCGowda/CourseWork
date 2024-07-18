import { BEST_PRODUCTS, PRODUCTS } from "../data/product";

export const getProducts = () => {
  return PRODUCTS.length ? PRODUCTS : [];
};

export const getProductById = (id) => {
  const filteredProduct = PRODUCTS.filter((product) => product.id === id);
  return filteredProduct.length ? filteredProduct[0] : {};
};

export const getProductByName = (name) => {
  const filteredProducts = PRODUCTS.filter((product) =>
    product.name.toLocaleLowerCase().includes(name)
  );
  return filteredProducts.length ? filteredProducts : {};
};

export const getProductByCategory = (category) => {
  if (category === "all") return PRODUCTS;
  const filteredProducts = PRODUCTS.filter(
    (product) => product.category === category
  );
  return filteredProducts.length ? filteredProducts : [];
};

export const getProductByMaker = (maker) => {
  const filteredProducts = PRODUCTS.filter(
    (product) => product.manufacturer === maker
  );
  return filteredProducts.length ? filteredProducts : [];
};

export const getBestProducts = () => {
  return BEST_PRODUCTS;
};

export const getAccessoriesById = (ids) => {
  const filteredProducts = PRODUCTS.filter(
    (product) => product.category === "accessory"
  );
  // Map over the array of ids and filter accessories for each id
  const accessories = ids.map((id) =>
    filteredProducts.filter((accessory) => accessory.id === id)
  );
  // The result will be an array of arrays, flatten it if needed
  const flattenedAccessories = accessories.flat();
  return flattenedAccessories;
};
