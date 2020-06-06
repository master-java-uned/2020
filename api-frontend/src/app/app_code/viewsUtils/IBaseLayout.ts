/**
 * Created by PeterFight (03/05/2020):
 * Interfaz para gestionar el contenido que no va en el body: metas, scripts y styles...
 * Los scripts los meto en al final del body (buena práctica) y los styles en el constructor de cada component
 */
interface IBaseLayout{
   metaTitle: string;
   metaDesc: string;
   metaKeys: Array<string>;
}
