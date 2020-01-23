/**
 * BookSotre Backend Restful Apis
 * Bookstore backend apis
 *
 * OpenAPI spec version: 0.0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


/**
 * Book Resource Representation
 */
export interface Book { 
    id?: number;
    title: string;
    description?: string;
    unitCost: number;
    isbn: string;
    publicationDate?: Date;
    numOfPages?: number;
    imageUrl?: string;
    language?: Book.LanguageEnum;
}
export namespace Book {
    export type LanguageEnum = 'ENGLISH' | 'FRANCH' | 'SPANINSH';
    export const LanguageEnum = {
        ENGLISH: 'ENGLISH' as LanguageEnum,
        FRANCH: 'FRANCH' as LanguageEnum,
        SPANINSH: 'SPANINSH' as LanguageEnum
    };
}