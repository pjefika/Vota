package controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.imageio.ImageIO;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import entidades.Celula;
import entidades.Dados;
import entidades.Evento;
import models.DadosServico;
import net.coobird.thumbnailator.Thumbnails;
import util.JSFUtil;

@ManagedBean
@ViewScoped
public class DadosBean {

	private Evento evento;

	private Dados dados;

	private Celula celula;

	private List<Dados> dadosFiltrados;

	private String path = null;

	@EJB
	private DadosServico dadosServico;

	public DadosBean() {

		this.dados = new Dados();

		this.evento = new Evento();

		this.celula = new Celula();

	}

	public void cadastrarDados(FileUploadEvent event) {

		UploadedFile file = event.getFile();

		try {

			if (this.evento != null && !this.celula.getNome().isEmpty()) {

				this.dadosServico.uploadImg(file, this.evento, this.celula);

				JSFUtil.addInfoMessage("Cadastrado com sucesso.");

			} else { 

				JSFUtil.addErrorMessage("Por favor selecione o Evento/Celula.");

			}			

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public void removerImg(Dados dados) {

		try {

			this.dadosServico.removerImg(dados);

			JSFUtil.addInfoMessage("Removido com sucesso.");

		} catch (Exception e) {

			JSFUtil.addErrorMessage(e.getMessage());

		}

	}

	public List<Dados> listarDados() {

		this.dadosFiltrados = this.dadosServico.listarDados(this.evento, this.celula);

		return this.dadosFiltrados;

	}

	public void listarDadosAction() {

		this.dadosFiltrados = this.dadosServico.listarDados(this.evento, this.celula);

	}

	/*public StreamedContent imagens(String path) throws Exception {

		StreamedContent img = new DefaultStreamedContent(new FileInputStream(path), "image/jpg");

		return img;

	}	

	public StreamedContent imagensFull() {

		try {

			StreamedContent img = new DefaultStreamedContent(new FileInputStream(this.path), "image/jpg");

			return img;

		} catch (Exception e) {

			return null;			

		}	

	}*/

	public void clearList() {

		this.dadosFiltrados = null;

	}

	public String getImgThumb(String path) throws Exception {

		BufferedImage originalImage = ImageIO.read(new File(path));

		BufferedImage thumbnail = Thumbnails.of(originalImage)
				.size(200, 200)
				.asBufferedImage();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(thumbnail, "jpg", baos );
		baos.flush();
		byte[] imageInByteArray = baos.toByteArray();
		baos.close();
		String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);

		return b64;

	}

	public String imgFull() throws Exception {

		BufferedImage bImage = ImageIO.read(new File(this.path));
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write( bImage, "jpg", baos );
		baos.flush();
		byte[] imageInByteArray = baos.toByteArray();
		baos.close();
		String b64 = javax.xml.bind.DatatypeConverter.printBase64Binary(imageInByteArray);

		return b64;

	}

	public Dados getDados() {
		return dados;
	}

	public void setDados(Dados dados) {
		this.dados = dados;
	}

	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}

	public Celula getCelula() {
		return celula;
	}

	public void setCelula(Celula celula) {
		this.celula = celula;
	}

	public List<Dados> getDadosFiltrados() {
		return dadosFiltrados;
	}

	public void setDadosFiltrados(List<Dados> dadosFiltrados) {
		this.dadosFiltrados = dadosFiltrados;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}	

}
