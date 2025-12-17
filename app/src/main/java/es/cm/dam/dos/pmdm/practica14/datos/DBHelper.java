package es.cm.dam.dos.pmdm.practica14.datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String NOMBRE_BD = "papeleria.db";
    public static final int VERSION_BD = 5;

    public DBHelper(Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {



        db.execSQL("create table Usuarios(id_usuario integer primary key autoincrement," +
                "usuario text," +
                "contrasenia text," +
                "nombre text)");

        db.execSQL("create table Productos(id_producto integer primary key autoincrement," +
                "nombre text," +
                "precio float," +
                "descripcion text)");

        db.execSQL("create table Carrito(id_carrito integer primary key autoincrement," +
                "id_usuario integer," +
                "id_producto integer," +
                "cantidad integer," +
                "foreign key(id_usuario) references Usuarios(id_usuario)," +
                "foreign key(id_producto) references Productos(id_producto))");

        db.execSQL("insert into Usuarios(usuario, contrasenia, nombre) values('user1', 'pass1', 'Alex')");
        db.execSQL("insert into Usuarios(usuario, contrasenia, nombre) values('user2', 'pass2', 'Asensio')");

        db.execSQL("insert into Productos values(1, 'Cuaderno A4 rayado', 2.50, 'Cuaderno de 80 hojas, tapa blanda')");
        db.execSQL("insert into Productos values(2, 'Bolígrafo azul', 0.80, 'Bolígrafo de tinta azul punta fina')");
        db.execSQL("insert into Productos values(3, 'Lápiz HB', 0.50, 'Lápiz grafito estándar para uso escolar')");
        db.execSQL("insert into Productos values(4, 'Marcador fluorescente amarillo', 1.20, 'Resaltador de color amarillo')");
        db.execSQL("insert into Productos values(5, 'Pegamento en barra 20g', 1.00, 'Pegamento en barra no tóxico')");
        db.execSQL("insert into Productos values(6, 'Tijeras escolares', 2.00, 'Tijeras de acero inoxidable con punta redonda')");
        db.execSQL("insert into Productos values(7, 'Carpeta de anillas', 3.50, 'Carpeta plástica tamaño A4 con 2 anillas')");
        db.execSQL("insert into Productos values(8, 'Regla de 30 cm', 0.90, 'Regla plástica transparente')");
        db.execSQL("insert into Productos values(9, 'Goma de borrar', 0.40, 'Goma blanca para lápiz')");
        db.execSQL("insert into Productos values(10, 'Juego de colores 12 unidades', 4.20, 'Lápices de colores de madera')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists Carrito");
        db.execSQL("drop table if exists Usuarios");
        db.execSQL("drop table if exists Productos");


        this.onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }
}
