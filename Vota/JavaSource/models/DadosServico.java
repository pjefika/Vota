package models;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.primefaces.model.UploadedFile;

import entidades.Celula;
import entidades.Dados;
import entidades.Evento;
import org.apache.axis.encoding.Base64;
import util.JSFUtil;

@Stateless
public class DadosServico {

    @PersistenceContext(unitName = "vu")
    private EntityManager entityManager;

    public void uploadImg(UploadedFile file, Evento evento, Celula celula) throws Exception {

        try {

            byte[] conteudo = file.getContents();

            String imgBase64 = Base64.encode(conteudo);
//            System.out.println(imgBase64);
//            String extencion = file.getFileName().substring(file.getFileName().lastIndexOf('.'), file.getFileName().length());
//
//            String path = "C:\\UploadedFiles\\" + JSFUtil.gerarStringAleatoria(6) + extencion;
//
//            FileOutputStream fos = new FileOutputStream(path);
//
//            fos.write(conteudo);
//            fos.close();
            this.cadastraDados(evento, imgBase64, file.getFileName(), celula);

        } catch (Exception e) {

            throw new Exception(e.getMessage());

        }

    }

    public void cadastraDados(Evento evento, String img, String nome, Celula celula) throws Exception {

        try {

            Dados dados = new Dados();

            dados.setEvento(evento);
            dados.setImg(img);
            dados.setNome(nome);
            dados.setCelula(celula);

            this.entityManager.persist(dados);

        } catch (Exception e) {

            throw new Exception("Erro ao cadastrar.");

        }

    }

    public void removerImg(Dados dados) throws Exception {

        try {

            this.removerDados(dados);

            File file = new File(dados.getImg());

            if (file.exists()) {

                file.delete();

            }

        } catch (Exception e) {

            throw new Exception(e.getMessage());

        }

    }

    public void removerDados(Dados dados) throws Exception {

        try {

            this.entityManager.remove(this.entityManager.contains(dados) ? dados : this.entityManager.merge(dados));

        } catch (Exception e) {

            throw new Exception(e.getMessage());

        }

    }

    @SuppressWarnings("unchecked")
    public List<Dados> listarDados(Evento evento, Celula celula) {

        try {

            Query query = this.entityManager.createQuery("FROM Dados d WHERE d.evento =:param1 AND d.celula =:param2");
            query.setParameter("param1", evento);
            query.setParameter("param2", celula);

            return query.getResultList();

        } catch (Exception e) {

            return new ArrayList<Dados>();

        }

    }

    public String trataImg(String path) throws Exception {

        try {

            BufferedImage bImage = ImageIO.read(new File(path));
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", baos);
            baos.flush();
            byte[] imageInByteArray = baos.toByteArray();
            baos.close();
            String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);

            return b64;

        } catch (Exception e) {

            throw new Exception(e.getMessage());

        }

    }

}
