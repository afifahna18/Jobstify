package com.epitychia.jobstify.utils

import com.epitychia.jobstify.R
import com.epitychia.jobstify.data.DataEntity

object DataDummy {

    fun generateDummyJob(): List<DataEntity>{

        val job = ArrayList<DataEntity>()

        job.add(
            DataEntity(
                121,
                "Fullstack Developer atau Senior Programmer",
                "Amanah Corp (PT. Amanah Karya Indonesia) adalah perusahaan Software House yang sedang berkembang saat ini dan berlokasi di Depok membutuhkan Anda untuk bergabung bersama tim hebat kami yang ingin berjuang meraih mimpi bersama kami dengan visi Menjadi sebuah perusahaan Teknologi Informasi terkemuka dan terdepan dalam menghasilkan produk dan jasa IT yang berkualitas yang mampu bersaing dalam dunia global saat ini",
                "Amanah Corp (PT. Amanah Karya Indonesia)",
                "On Site, Depok, Jawa Barat",
                "- Berpengalaman lebih dari 1 tahun di Laravel atau NodeJs\n" +
                        "- Diutamamakan juga yang pernah pakai React Js, Vue JS\n" +
                        "- Bisa mengimplemasikan API\n" +
                        "- Memiliki semangat untuk belajar hal-hal yang baru\n" +
                        "- Memiliki perilaku yang baik\n" +
                        "- Jujur dan Amanah dalam pekerjaan",
                "Amanah Corp (PT. Amanah Karya Indonesia)",
                "Artist",
                R.drawable.job_it_amanah_corp,
            )
        )
        job.add(
            DataEntity(
                122,
                "Intermediate/Senior Backend Engineer",
                "- Design, build, and maintain our products with a focus on customer needs\n" +
                    "- Architect product infrastructure to ensure reliability, scalability, and security\n" +
                    "- Collaborate with engineering teams across the company to build new features at scale\n" +
                    "- Maintain our existing application; tech stack(node.js, mongodb, docker, kubernetes, express)",
                "PT Bangun Digital Nusantara",
                "On Site, Malang, Jawa Timur",
            "-",
                "1. Gaji standar Jakarta - Hidup di Malang\n" +
                        "2. Makan siang gratis\n" +
                        "3. BPJS TK, BPJS Kesehatan\n" +
                        "4. Cuti tahunan langsung",
                "IT",
                R.drawable.job_it_bdn

            )
        )
        job.add(
            DataEntity(
                123,
                "HEAD CHEF",
                "Merencanakan dan mengatur persiapan makanan dan semua kegiatan dapur\n" +
                        "Mengetahui trend makanan yang sedang berlangsung, selera konsumen, dan bahan spesial maupun cara memproses makanan yang sedang populer\n" +
                        "Melatih, memberikan arahan, serta membuat standar pembuatan suatu makanan\n" +
                        "Mengestimasi keperluan bahan makanan dan juga biaya pembuatan suatu menu\n" +
                        "Mengawasi kegiatan operasional dapur; termasuk pembelian bahan makanan dan keperluan dapur, mengawasi pembelian, penjualan, dan cost, dan mengawasi jadwal karyawan\n" +
                        "Mengatur pembelian, perawatan dan perbaikan peralatan dapur\n" +
                        "Merekrut dan mengatur karyawan dapur\n" +
                        "Melakukan kegiatan administratif yang berhubungan dengan dapur",
                "Toby's Estate",
                "Jakarta Raya",
                "Memiliki pengalaman kerja min. 5 (lima) tahun di bidang dan posisi yang sama\n" +
                        "Mampu mengatur dapur dengan sangat baik\n" +
                        "Mampu mengetahui dan menyelesaikan masalah secara efisien\n" +
                        "Mampu multi-task dan mengawasi beberapa bagian dapur sekaligus\n" +
                        "Mampu mendelegasikan tugas dan tanggung jawab\n" +
                        "Memiliki kemampuan komunikasi dan kepemimpinan yang baik\n" +
                        "Memiliki passion terhadap masakan dan tidak berkompromi terhadap kualitas\n" +
                        "Fasih berkomunikasi dengan bahasa Inggris, baik lisan maupun tulisan",
                "-",
                "Chef",
                R.drawable.job_chef_toby
            )
        )
        return job
    }
}